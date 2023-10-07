package spring.edu.monglesson.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import spring.edu.monglesson.kafka.KafkaService;

@Component
public class TransactionConsumer {
    private final KafkaService kafkaService;

    @Autowired
    public TransactionConsumer(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    Logger LOG = LoggerFactory.getLogger(TransactionConsumer.class);

    private final String TOPIC_LESSON = "lessson";

    @KafkaListener(topics = TOPIC_LESSON)
    void consumeCourseID(String courseID) {
        LOG.info(courseID);
        kafkaService.careateLesson(courseID);
    }
}
