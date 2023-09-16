package spring.edu.monglesson.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {
    Logger LOG = LoggerFactory.getLogger(TransactionConsumer.class);

    private final String TOPIC_LESSON = "lessson";

    @KafkaListener(topics = TOPIC_LESSON)
    void consumeCourseID(String courseID) {
        LOG.info(courseID);

    }
}
