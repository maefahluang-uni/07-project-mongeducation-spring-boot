package spring.edu.monglesson.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {
    private final String TOPIC_LESSON = "lessson";


    @KafkaListener (topics = TOPIC_LESSON)
    void consumeStudentCreateEvent(String transaction) {
    }
}
