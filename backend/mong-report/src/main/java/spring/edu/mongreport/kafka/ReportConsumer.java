package spring.edu.mongreport.kafka;

import spring.edu.mongreport.ReportController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReportConsumer {

    @Autowired
    private ReportController reportController;

    // recieved data from student entity class.
    @KafkaListener(topics = "student.regist")
    void consumeCreateReportEvent(List<Integer> recievePackaged) {
        reportController.createReportByEnroll(
            Integer.toString(recievePackaged.get(0)), 
            Integer.toString(recievePackaged.get(1))
        );
    }

}
