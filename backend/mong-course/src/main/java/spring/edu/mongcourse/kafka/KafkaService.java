package spring.edu.mongcourse.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Long> kafkaTemplate;

    private final String TOPIC_LESSON = "lessson";

    @Autowired
    public KafkaService(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCourseIdToLesson(Long id) {
        kafkaTemplate.send(TOPIC_LESSON, id);
    }

}
