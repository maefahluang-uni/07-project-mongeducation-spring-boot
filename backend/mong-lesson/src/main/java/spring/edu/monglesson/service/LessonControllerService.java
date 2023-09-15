package spring.edu.monglesson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.kafka.KafkaService;
import spring.edu.monglesson.model.Lesson;
import spring.edu.monglesson.model.LessonDTO;
import spring.edu.monglesson.repository.LessonRepository;

@Service
public class LessonControllerService {
    private final LessonRepository lessonRepository;
    private final KafkaService kafkaService;
    private ServerMapper serverMapper;

    @Autowired
    public LessonControllerService(LessonRepository lessonRepository, KafkaService kafkaService,
            ServerMapper serverMapper) {
        this.lessonRepository = lessonRepository;
        this.kafkaService = kafkaService;
        this.serverMapper = serverMapper;
    }

    public List<Lesson> getAllLesson() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons;
    }

    public Optional<Lesson> getLessonById(Long id) {
        Optional<Lesson> optLesson = lessonRepository.findById(id);
        return optLesson;
    }

    public void saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void mapperLesson(LessonDTO lessonDTO, Lesson lesson) {
        serverMapper.updateLessonFromDto(lessonDTO, lesson);
        saveLesson(lesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    public void deleteAllLesson() {
        lessonRepository.deleteAll();
    }
}
