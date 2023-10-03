package spring.edu.mongstudent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    // non-relational relationship of restful API (classic). 

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (!studentOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        Student student = studentOpt.get();
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        
        // Provide data for kafka.
        kafkaTemplate.send("student.regist", student.getId());
        return ResponseEntity.ok("student created.");
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
         if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        studentRepository.save(student);
        return ResponseEntity.ok("student updated.");
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody StudentDTO studentDto) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (!studentOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        Student student = studentOpt.get();
        serverMapper.updateStudentFromDto(studentDto, student);
        studentRepository.save(student);
        
        return ResponseEntity.ok("student patched.");
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok("student deleted.");
    }

    @DeleteMapping("/students")
    public ResponseEntity<String> deleteAllStudents() {
        studentRepository.deleteAll();
        return ResponseEntity.ok("all students deleted.");
    }

}
