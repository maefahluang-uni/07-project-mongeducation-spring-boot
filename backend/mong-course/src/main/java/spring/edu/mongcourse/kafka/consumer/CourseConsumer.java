package spring.edu.mongcourse.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import spring.edu.mongcourse.controller.CourseController;
import spring.edu.mongcourse.model.Course;

@Component
public class CourseConsumer {

    @Autowired
    private CourseController courseController;

    @KafkaListener(topics = "teacherToCourse")
    void consumeCreateCourseEvent(String teacher_id) {
        Course course = new Course();
        course.setTeacherID(teacher_id);
        courseController.postCourse(course);
    }
}
