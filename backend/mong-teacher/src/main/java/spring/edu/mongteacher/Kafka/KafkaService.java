package spring.edu.mongteacher.Kafka;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.edu.mongteacher.domain.Teacher;
import spring.edu.mongteacher.repository.TeacherRepository;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Long> kafkaTemplate;
    private final TeacherRepository teacherRepository;

    @Autowired
    public KafkaService(KafkaTemplate<String, Long> kafkaTemplate,TeacherRepository teacherRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.teacherRepository = teacherRepository;
    }

    //send id to course
    @PostMapping("/Courses/Teacher/{id}")
    public ResponseEntity<String> sendIdToCourse(@PathVariable Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        kafkaTemplate.send("teacherToCourse",teacher.get().getId());
        return ResponseEntity.ok("Send id success");
    }
}
