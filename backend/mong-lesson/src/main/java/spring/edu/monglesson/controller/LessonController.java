package spring.edu.monglesson.controller;

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

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.model.Lesson;
import spring.edu.monglesson.model.LessonDTO;
import spring.edu.monglesson.repository.LessonRepository;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private LessonRepository lessonRepository;
    private KafkaTemplate<String, Lesson> kafkaTemplate;

    private ServerMapper serverMapper;


    @Autowired
    public LessonController(LessonRepository lessonRepository, KafkaTemplate<String, Lesson> kafkaTemplate, ServerMapper serverMapper) {
        this.lessonRepository = lessonRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getLessonById(@PathVariable Long id) {
        Optional<Lesson> optLesson = lessonRepository.findById(id);
        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Lesson is found\n" + optLesson);
    }

    @PostMapping
    public ResponseEntity<String> createLesson(@RequestBody Lesson lesson) {
        lessonRepository.save(lesson);
        return ResponseEntity.ok("Lesson was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        Optional<Lesson> optLesson = lessonRepository.findById(id);
        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
        }
        lesson.setId(id);
        lessonRepository.save(lesson);
        return ResponseEntity.ok("Lesson was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        Optional<Lesson> optLesson = lessonRepository.findById(id);

        if (!optLesson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found");
        }

        Lesson lesson = optLesson.get();
        serverMapper.updateLessonFromDto(lessonDTO, lesson);
        lessonRepository.save(lesson);

        return ResponseEntity.ok("Lesson patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        lessonRepository.deleteById(id);
        return ResponseEntity.ok("Lesson was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllLesson() {
        lessonRepository.deleteAll();
        return ResponseEntity.ok("Lesson was Deleted");
    }

}
