package spring.edu.monglesson.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {
    Logger LOG = LoggerFactory.getLogger(TransactionConsumer.class);
    private final String LESSON_TOPIC = "lesson";
    private final String LESSON_GROUP = "lesson-group";

    @KafkaListener(topics = LESSON_TOPIC, groupId = LESSON_GROUP)
    void listener(String lessonID) {
        LOG.info(lessonID);
    }
}