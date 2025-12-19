package KafkaStudy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class ProducerErrorHandling {

    /**
     * Scenario 1: Basic Idempotence (No Transactions)
     * How to handle failures in the Callback.
     */
    public static void handleCallbackFailure(KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", "value");

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.err.println("Fatal error sending message: " + exception.getMessage());
                    
                    // 1. Analyze the Exception
                    if (exception instanceof OutOfOrderSequenceException) {
                        // This confirms the "Broken Chain" scenario.
                        // The broker received a sequence number it didn't expect.
                        // CRITICAL: This producer is now in a bad state.
                        System.err.println("Sequence broken! Producer instance must be closed/recreated.");
                        producer.close(); 
                        // Trigger application shutdown or recreation logic
                    } else if (exception instanceof org.apache.kafka.common.errors.TimeoutException) {
                        // delivery.timeout.ms expired.
                        // Data might be lost.
                        System.err.println("Timeout reached. Data lost for this record.");
                        // Option A: Save to local disk/DB (Dead Letter Queue)
                        // Option B: Terminate application to stop processing subsequent ordered data
                    }
                } else {
                    System.out.println("Success: " + metadata.offset());
                }
            }
        });
    }

    /**
     * Scenario 2: Transactions (Recommended for robust Exactly Once)
     * This solves the "Broken Chain" problem by making the batch atomic.
     * If one fails, we abort the whole transaction, so Consumers (read_committed) see NOTHING.
     */
    public static void sendWithTransactions(Properties props) {
        props.put("transactional.id", "my-unique-transactional-id");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 1. Initialize
        producer.initTransactions();

        try {
            // 2. Start Transaction
            producer.beginTransaction();

            for (int i = 0; i < 10; i++) {
                producer.send(new ProducerRecord<>("my-topic", "msg-" + i));
            }

            // 3. Commit
            // If any send() failed internally (e.g. timeout), commitTransaction() will throw an exception.
            producer.commitTransaction();
            
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // Fatal errors: We cannot recover this producer instance.
            producer.close();
            throw e; 
        } catch (KafkaException e) {
            // "Broken Chain" or Timeout scenarios usually land here.
            // 4. Abort
            // We abort the transaction. Consumers will NOT see partial messages (1..5 success, 6 fail).
            // They see nothing from this batch.
            System.err.println("Error occurred, aborting transaction: " + e.getMessage());
            producer.abortTransaction();
            
            // 5. Retry Logic
            // The application can now safely decide to retry the whole batch from the beginning.
        }
    }
}
