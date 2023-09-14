package spring.edu.mongteacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherContoller {
    @Autowired
    private TeacherRepository teacherRepository;

    //get all teachers
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        return ResponseEntity.ok().body(teacherRepository.findAll());
    }

    //post teacher
    @PostMapping("/teachers")
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher){
        teacherRepository.save(teacher);
        return ResponseEntity.ok().body("created");
    }
}
