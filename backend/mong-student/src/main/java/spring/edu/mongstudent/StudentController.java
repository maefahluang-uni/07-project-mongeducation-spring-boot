package spring.edu.mongstudent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.edu.mongstudent.kafka.KafkaServices;



@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private KafkaServices kafkaServices;

    // non-relational relationship of restful API (classic). 

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (!studentOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        Student student = studentOpt.get();
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        // Check if the username already exists in the repository
        if (studentRepository.existsByUsername(student.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use");
        }

        studentRepository.save(student);
        return ResponseEntity.ok("student created.");
    }

    @PostMapping("/{student_id}/enroll/{course_id}")
    public ResponseEntity<String> enrollCourseByStudent(@PathVariable Long student_id, @PathVariable Long course_id) {
        // Provide data for kafka.
        return kafkaServices.registStudentIdAndCourseId(student_id, course_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
         if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        studentRepository.save(student);
        return ResponseEntity.ok("student updated.");
    }

    @PatchMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found.");
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok("student deleted.");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllStudents() {
        studentRepository.deleteAll();
        return ResponseEntity.ok("all students deleted.");
    }


     // post check login
     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody Student student) {
         List<Student> studentCheckUsername = studentRepository.findByUsername(student.getUsername());
 
         if (studentCheckUsername.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("username not found");
         }
 
         Student foundStudent = studentCheckUsername.get(0);
         if (!foundStudent.getPassword().equals(student.getPassword())) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
         }
         return ResponseEntity.ok(studentCheckUsername);
     }

}
