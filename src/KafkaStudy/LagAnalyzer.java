package KafkaStudy;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 工具类：用于诊断 Kafka Group Lag 无法消除的问题
 */
public class LagAnalyzer {

    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String groupId = "my-group-id"; // 替换为你的 Group ID
        String topic = "my-topic";      // 替换为你的 Topic

        analyzeLag(bootstrapServers, groupId, topic);
    }

    public static void analyzeLag(String bootstrapServers, String groupId, String topic) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        
        // 重要：先用 read_uncommitted 模式看看能不能读到这些“幽灵”消息
        // 如果 read_uncommitted 能读到，但你的应用（read_committed）读不到，说明是“事务未提交”问题
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");
        
        // 自动提交关掉，以免影响生产环境 offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 1. 获取 Topic 的分区信息
            Map<TopicPartition, Long> endOffsets = new HashMap<>();
            consumer.partitionsFor(topic).forEach(partitionInfo -> {
                TopicPartition tp = new TopicPartition(topic, partitionInfo.partition());
                // 获取当前 Log End Offset (LEO)
                Map<TopicPartition, Long> result = consumer.endOffsets(Collections.singleton(tp));
                endOffsets.put(tp, result.get(tp));
            });

            // 2. 获取 Group 当前提交的 Offset
            Map<TopicPartition, Long> committedOffsets = new HashMap<>();
            endOffsets.keySet().forEach(tp -> {
                OffsetAndMetadata committed = consumer.committed(tp);
                if (committed != null) {
                    committedOffsets.put(tp, committed.offset());
                } else {
                    committedOffsets.put(tp, 0L);
                }
            });

            // 3. 分析每个分区的 Lag
            System.out.println("====== Partition Lag Analysis ======");
            long totalLag = 0;
            for (TopicPartition tp : endOffsets.keySet()) {
                long leo = endOffsets.get(tp);
                long current = committedOffsets.get(tp);
                long lag = leo - current;
                totalLag += lag;
                System.out.printf("Partition %d: Current=%d, LEO=%d, Lag=%d%n", 
                        tp.partition(), current, leo, lag);
                
                if (lag > 0) {
                    // 4. 尝试试探性读取这些 Lag 消息
                    diagnoseMessages(consumer, tp, current, lag);
                }
            }
            
            System.out.println("\nTotal Lag: " + totalLag);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void diagnoseMessages(KafkaConsumer<String, String> consumer, TopicPartition tp, long startOffset, long count) {
        System.out.printf("\n--- Diagnosing %d messages in Partition %d (Offset %d) ---%n", count, tp.partition(), startOffset);
        
        try {
            consumer.assign(Collections.singletonList(tp));
            consumer.seek(tp, startOffset);
            
            // 尝试拉取一次
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            
            if (records.isEmpty()) {
                System.err.println("警告：Lag 显示有消息，但无法 poll 到任何数据！");
                System.err.println("可能原因：");
                System.err.println("1. 这些是【未提交的事务消息】(Aborted/Open Transactions)。");
                System.err.println("   如果是这种情况，Lag 监控通常基于 HW，而 Consumer 只能读到 LSO。");
                System.err.println("2. 消息已过期被清理，但 Offset 没更新（较少见）。");
            } else {
                System.out.println("成功读取到 " + records.count() + " 条消息。");
                // 检查第一条消息
                ConsumerRecord<String, String> first = records.iterator().next();
                System.out.println("第一条消息示例: Key=" + first.key() + ", Value=" + first.value());
                System.out.println("如果能读到但平时不消费，可能是：");
                System.out.println("1. 反序列化失败（Poison Pill）导致 Consumer 线程崩溃/卡死。");
                System.out.println("2. 业务逻辑抛出异常导致无法提交 Offset。");
            }
        } catch (Exception e) {
            System.err.println("读取时发生异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
