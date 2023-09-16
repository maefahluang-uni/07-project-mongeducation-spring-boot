package spring.edu.monglesson.controller;

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

import spring.edu.monglesson.model.Lesson;
import spring.edu.monglesson.model.LessonDTO;
import spring.edu.monglesson.service.LessonControllerService;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonControllerService lessonControllerService;

    @Autowired
    public LessonController(LessonControllerService lessonControllerService) {
        this.lessonControllerService = lessonControllerService;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonControllerService.getAllLesson();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lesson>> getLessonById(@PathVariable Long id) {
        Optional<Lesson> optLesson = lessonControllerService.getLessonById(id);
        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optLesson);
    }

    @GetMapping("/course/{courseID}")
    public ResponseEntity<List<Lesson>> getLessonByCourse(@PathVariable String courseID) {
        List<Lesson> lessons = lessonControllerService.getLessonByCourseID(courseID);
        return ResponseEntity.ok(lessons);
    }

    @PostMapping
    public ResponseEntity<String> postLesson(@RequestBody Lesson lesson) {
        lessonControllerService.saveLesson(lesson);
        return ResponseEntity.ok("Lesson was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        Optional<Lesson> optLesson = lessonControllerService.getLessonById(id);
        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
        }
        lessonControllerService.saveLesson(lesson);
        return ResponseEntity.ok("Lesson was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        Optional<Lesson> optLesson = lessonControllerService.getLessonById(id);
        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
        }
        lessonControllerService.mapperLesson(lessonDTO, optLesson.get());
        return ResponseEntity.ok("Lesson patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        lessonControllerService.deleteLesson(id);
        return ResponseEntity.ok("Lesson was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllLesson() {
        lessonControllerService.deleteAllLesson();
        return ResponseEntity.ok("Lesson was Deleted");
    }

}
