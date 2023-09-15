package spring.edu.mongreport.kafka;

import spring.edu.mongreport.ReportController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReportConsumer {

    @Autowired
    private ReportController reportController;

    @KafkaListener (topics = "student.regist")
    void consumeStudentCreateEvent(String studentId) {
        reportController.createReports(studentId);
    }
}
