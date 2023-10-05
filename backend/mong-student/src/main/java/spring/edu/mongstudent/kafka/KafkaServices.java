package spring.edu.mongstudent.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import spring.edu.mongstudent.StudentController;

@Service
public class KafkaServices {
    private final KafkaTemplate<String, List<Long>> kafkaTemplate;

    @Autowired
    private StudentController studentController;

    @Autowired
    public KafkaServices(KafkaTemplate<String, List<Long>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<String> registStudentIdAndCourseId(Long student_id, Long course_id) {
        ResponseEntity<?> student = studentController.getStudentById(student_id);
        if (student.getStatusCodeValue() != 200) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        List<Long> registPackage = new ArrayList<Long>();
        registPackage.add(student_id);
        registPackage.add(course_id);

        kafkaTemplate.send("student.regist", registPackage);
        // kafkaTemplate.send("course.regist", course_id);
        return ResponseEntity.ok("studentId and courseId was sended.");
    }

}