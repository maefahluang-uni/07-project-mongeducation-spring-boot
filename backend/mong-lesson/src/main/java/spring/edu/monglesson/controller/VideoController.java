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
import spring.edu.monglesson.model.Video;
import spring.edu.monglesson.model.VideoDTO;
import spring.edu.monglesson.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private VideoRepository videoRepository;
    private KafkaTemplate<String, Video> kafkaTemplate;

    private ServerMapper serverMapper;

    @Autowired
    public VideoController(VideoRepository videoRepository, KafkaTemplate<String, Video> kafkaTemplate,
            ServerMapper serverMapper) {
        this.videoRepository = videoRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> Videos = videoRepository.findAll();
        return ResponseEntity.ok(Videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVideoById(@PathVariable Long id) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Video is found\n" + optVideo);
    }

    @GetMapping("/{lesson}")
    public ResponseEntity<String> getVideoById(@PathVariable Lesson lesson) {
        List<Video> optVideo = videoRepository.findByLesson(lesson);
        return ResponseEntity.status(HttpStatus.OK).body("Video in " + lesson + "\n" + optVideo);
    }

    @PostMapping
    public ResponseEntity<String> createVideo(@RequestBody Video Video) {
        videoRepository.save(Video);
        return ResponseEntity.ok("Video was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVideo(@PathVariable Long id, @RequestBody Video Video) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }
        Video.setId(id);
        videoRepository.save(Video);
        return ResponseEntity.ok("Video was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody VideoDTO VideoDTO) {
        Optional<Video> optVideo = videoRepository.findById(id);

        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }

        Video Video = optVideo.get();
        serverMapper.updateVideoFromDto(VideoDTO, Video);
        videoRepository.save(Video);

        return ResponseEntity.ok("Video patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
        return ResponseEntity.ok("Video was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllVideo() {
        videoRepository.deleteAll();
        return ResponseEntity.ok("Video was Deleted");
    }

}
