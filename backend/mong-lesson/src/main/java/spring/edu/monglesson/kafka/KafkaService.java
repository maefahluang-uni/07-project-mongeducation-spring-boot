package spring.edu.monglesson.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; 
    }

    private final String LESSON_TOPIC = "lesson";

    public void sendLessonID(Long lessonID) {
        kafkaTemplate.send(LESSON_TOPIC, lessonID);
    }
}
