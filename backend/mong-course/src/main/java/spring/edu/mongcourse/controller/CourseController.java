package spring.edu.mongcourse.controller;

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
import spring.edu.mongcourse.model.Course;
import spring.edu.mongcourse.model.CourseDTO;
import spring.edu.mongcourse.service.CourseControllerService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseControllerService courseControllerService;

    @Autowired
    public CourseController(CourseControllerService courseControllerService) {
        this.courseControllerService = courseControllerService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseControllerService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable Long id) {
        Optional<Course> optCourse = courseControllerService.getCourseById(id);
        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optCourse);
    }

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<List<Course>> getCourseByCategoryID(@PathVariable String categoryID) {
        List<Course> courses = courseControllerService.getCourseByCategoryID(categoryID);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/teacher/{teacherID}")
    public ResponseEntity<List<Course>> getCourseByTeacherID(@PathVariable String teacherID) {
        List<Course> courses = courseControllerService.getCourseByTeacherID(teacherID);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<String> postCourse(@RequestBody Course Course) {
        courseControllerService.saveCourse(Course);
        return ResponseEntity.ok("Course was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> optCourse = courseControllerService.getCourseById(id);
        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        courseControllerService.saveCourse(course);
        return ResponseEntity.ok("Course was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        Optional<Course> optCourse = courseControllerService.getCourseById(id);
        if (!optCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        courseControllerService.mapperCourse(courseDTO, optCourse.get());
        return ResponseEntity.ok("Course patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseControllerService.deleteCourse(id);
        return ResponseEntity.ok("Course was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCourse() {
        courseControllerService.deleteAllCourse();
        return ResponseEntity.ok("Course was Deleted");
    }
}
