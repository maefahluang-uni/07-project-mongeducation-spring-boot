package spring.edu.mongteacher.controller;

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
import org.springframework.web.bind.annotation.RestController;

import spring.edu.mongteacher.domain.Bank;
import spring.edu.mongteacher.domain.Teacher;
import spring.edu.mongteacher.dto.TeacherDTO;
import spring.edu.mongteacher.mapper.ServerMapper;
import spring.edu.mongteacher.repository.BankRepository;
import spring.edu.mongteacher.repository.TeacherRepository;

@RestController
public class TeacherContoller {

    private final BankRepository bankRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    private final ServerMapper serverMapper;

    @Autowired
    public TeacherContoller(BankRepository bankRepository, TeacherRepository teacherRepository,
            ServerMapper serverMapper) {
        this.bankRepository = bankRepository;
        this.teacherRepository = teacherRepository;
        this.serverMapper = serverMapper;
    }

    // get all teachers
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeacher() {
        return ResponseEntity.ok().body(teacherRepository.findAll());
    }

    // get by id
    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not found");
        }
        return ResponseEntity.ok(teacher);
    }

    // post check login
    @PostMapping("/teachers/login")
    public ResponseEntity<?> login(@RequestBody Teacher teacher) {
        List<Teacher> teacherCheckUserName = teacherRepository.findByUserName(teacher.getUserName());

        if (teacherCheckUserName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userName not found");
        }

        Teacher foundTeacher = teacherCheckUserName.get(0);
        if (!foundTeacher.getPassWord().equals(teacher.getPassWord())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }
        return ResponseEntity.ok(teacherCheckUserName);
    }

    // post teacher
    @PostMapping("/teachers")
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {
        //check username same
        if(!teacher.getFirstName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username is already");
        }
        teacherRepository.save(teacher);
        return ResponseEntity.ok().body("created");
    }

    // post teacher and bank
    @PutMapping("/teachers/{teacherID}/banks/{bankID}")
    public ResponseEntity<String> createTeacher(@PathVariable Long teacherID, @PathVariable Long bankID) {
        Bank bank = bankRepository.findById(bankID).orElse(null);
        Optional<Teacher> teachers = teacherRepository.findById(teacherID);
        Teacher teacher = new Teacher();
        teacher = teachers.get();
        teacher.setBankID(bank);
        teacherRepository.save(teacher);
        return ResponseEntity.ok().body("created");
    }

    // update some field
    @PatchMapping("/teachers/{id}")
    public ResponseEntity<String> patchTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        Optional<Teacher> optTeacher = teacherRepository.findById(id);
        if (!optTeacher.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
        Teacher teacher = optTeacher.get();
        serverMapper.updateTeacherFromDto(teacherDTO, teacher);
        teacherRepository.save(teacher);

        return ResponseEntity.ok("updated");
    }

    // delete teacher
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        Optional<Teacher> optTeacher = teacherRepository.findById(id);
        if (!optTeacher.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
        teacherRepository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
