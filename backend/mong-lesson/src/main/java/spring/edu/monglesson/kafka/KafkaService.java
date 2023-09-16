package spring.edu.monglesson.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import spring.edu.monglesson.controller.LessonController;
import spring.edu.monglesson.model.Lesson;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final LessonController lessonController;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, LessonController lessonController) {
        this.kafkaTemplate = kafkaTemplate;
        this.lessonController = lessonController;
    }

    public void careateLesson(String courseID) {
        Lesson lesson = new Lesson();
        lesson.setCourseID(courseID);
        lessonController.postLesson(lesson);
    }   
}
