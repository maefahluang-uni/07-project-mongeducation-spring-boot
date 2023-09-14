package spring.edu.mongcourse.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.edu.mongcourse.ServerMapper;
import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.model.Course;
import spring.edu.mongcourse.model.CourseDTO;
import spring.edu.mongcourse.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseRepository courseRepository;
    private KafkaTemplate<String, Course> kafkaTemplate;

    private ServerMapper serverMapper;

    @Autowired
    public CourseController(CourseRepository courseRepository, KafkaTemplate<String, Course> kafkaTemplate,
            ServerMapper serverMapper) {
        this.courseRepository = courseRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> Courses = courseRepository.findAll();
        return ResponseEntity.ok(Courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Long id) {
        Optional<Course> optCourse = courseRepository.findById(id);
        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optCourse);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCourseByCategory(@PathVariable Category category) {
        List<Course> courses = courseRepository.findByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return ResponseEntity.ok("Course was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> optCourse = courseRepository.findById(id);
        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        course.setId(id);
        courseRepository.save(course);
        return ResponseEntity.ok("Course was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        Optional<Course> optCourse = courseRepository.findById(id);

        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

        Course course = optCourse.get();
        serverMapper.updateCourseFromDto(courseDTO, course);
        courseRepository.save(course);

        return ResponseEntity.ok("Course patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return ResponseEntity.ok("Course was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCourse() {
        courseRepository.deleteAll();
        return ResponseEntity.ok("Course was Deleted");
    }
}
