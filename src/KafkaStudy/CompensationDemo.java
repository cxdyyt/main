package KafkaStudy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * 演示最终发送失败时的补偿机制（存入数据库 + 定时任务重试）
 */
public class CompensationDemo {

    // 模拟数据库连接
    private static Connection getConnection() throws Exception {
        // 这里仅作演示，实际应使用连接池
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "pass");
    }

    /**
     * 核心方法：发送消息，如果彻底失败，则降级存入数据库
     */
    public void sendMessageSafe(KafkaProducer<String, String> producer, String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        try {
            // 使用异步发送 + Callback
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        System.err.println("Kafka发送最终失败: " + exception.getMessage());
                        
                        // !!! 关键点：进入补偿流程 !!!
                        saveToDatabase(topic, key, value, exception.getMessage());
                    } else {
                        // 发送成功
                    }
                }
            });
        } catch (Exception e) {
            // 捕获 send() 方法本身的同步异常（如 buffer 满、序列化失败）
            System.err.println("调用send方法失败: " + e.getMessage());
            saveToDatabase(topic, key, value, e.getMessage());
        }
    }

    /**
     * 将失败消息持久化到数据库
     * 表结构建议：
     * CREATE TABLE kafka_failed_msg (
     *    id BIGINT AUTO_INCREMENT PRIMARY KEY,
     *    topic VARCHAR(100),
     *    msg_key VARCHAR(255),
     *    msg_value TEXT,
     *    error_reason VARCHAR(500),
     *    status INT DEFAULT 0, -- 0:待重试, 1:已重试, 2:放弃
     *    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     * );
     */
    private void saveToDatabase(String topic, String key, String value, String reason) {
        // 在生产环境中，这里应该是一个独立的、健壮的 Service 调用
        // 甚至可以写本地磁盘文件作为双重保险（以防 DB 也挂了）
        
        String sql = "INSERT INTO kafka_failed_msg (topic, msg_key, msg_value, error_reason) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, topic);
            ps.setString(2, key);
            ps.setString(3, value);
            ps.setString(4, reason);
            ps.executeUpdate();
            
            System.out.println("消息已保存至数据库，等待定时任务重试。Key: " + key);
            
        } catch (Exception dbEx) {
            // 最坏的情况：Kafka 挂了，数据库也挂了
            // 最后的防线：记录到本地日志文件（Log4j/Logback）
            System.err.println("严重故障：数据库补偿也失败了！写入本地日志: " + value);
        }
    }

    /**
     * 模拟定时任务（通常由 Spring Schedule, Quartz, XXL-JOB 等触发）
     * 逻辑：扫描 status=0 的消息 -> 重新发送 -> 成功后更新 status=1
     */
    public void retryJob(KafkaProducer<String, String> producer) {
        // 伪代码：
        // 1. SELECT * FROM kafka_failed_msg WHERE status = 0 LIMIT 100;
        // 2. 遍历每一条消息：
        //      producer.send(record, (metadata, ex) -> {
        //          if (ex == null) {
        //              UPDATE kafka_failed_msg SET status = 1 WHERE id = ?;
        //          } else {
        //              // 记录重试次数，超过 N 次告警人工处理
        //          }
        //      });
    }
}
