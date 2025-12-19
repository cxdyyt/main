package KafkaStudy;

import java.util.Properties;

/**
 * Example configuration for Kafka Producer to achieve "Exactly Once" semantics.
 * 
 * To achieve Exactly Once (EoS), we rely on the Idempotent Producer.
 * When idempotence is enabled, the producer retry count defaults to Integer.MAX_VALUE.
 */
public class ProducerConfiguration {

    public static Properties getExactlyOnceProducerConfig() {
        Properties props = new Properties();
        
        // 1. Bootstrap servers
        props.put("bootstrap.servers", "localhost:9092");
        
        // 2. Enable Idempotence
        // This is the key configuration for exactly-once delivery per partition.
        // It ensures that messages are delivered exactly once in order.
        // Defaults to true in Kafka clients >= 3.0, but explicit configuration is safer for older versions.
        props.put("enable.idempotence", "true");
        
        // 3. Acks
        // Must be set to "all" (or "-1") for idempotence to work effectively and guarantee durability.
        // If enable.idempotence is true, this defaults to "all".
        props.put("acks", "all");
        
        // 4. Retries
        // The user asked specifically about this.
        // For exactly-once semantics, retries must be > 0.
        // With enable.idempotence=true, the default is Integer.MAX_VALUE (2147483647).
        // It is recommended to leave it at the maximum or a very high number, 
        // allowing the producer to retry indefinitely until the delivery.timeout.ms is reached.
        props.put("retries", Integer.MAX_VALUE);
        
        // 5. Max In-Flight Requests
        // To guarantee ordering while retrying, this must be <= 5.
        // If enable.idempotence is true, the driver enforces this.
        props.put("max.in.flight.requests.per.connection", "5");
        
        // Optional: Transactional ID for cross-partition exactly-once
        // props.put("transactional.id", "my-transactional-id");
        
        // Serializers
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        return props;
    }
    
    public static void main(String[] args) {
        Properties config = getExactlyOnceProducerConfig();
        System.out.println("Kafka Producer Configuration for Exactly Once:");
        config.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
