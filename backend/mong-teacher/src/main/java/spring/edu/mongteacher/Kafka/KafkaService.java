package spring.edu.mongteacher.Kafka;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spring.edu.mongteacher.domain.Teacher;
import spring.edu.mongteacher.repository.TeacherRepository;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<String> sendOutTeacherId(Long id) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        if (!teacherOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("teacher not found.");
        }

        Teacher teacher = teacherOpt.get();
        kafkaTemplate.send("teacherToCourse",Long.toString(teacher.getId()));
        // kafkaTemplate.send("course.regist", course_id);
        return ResponseEntity.ok("teacherId was sended.");
    }

}
