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

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.model.Lesson;
import spring.edu.monglesson.model.Video;
import spring.edu.monglesson.model.VideoDTO;
import spring.edu.monglesson.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private VideoRepository videoRepository;

    private ServerMapper serverMapper;

    @Autowired
    public VideoController(VideoRepository videoRepository, ServerMapper serverMapper) {
        this.videoRepository = videoRepository;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Video>> getVideoById(@PathVariable Long id) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optVideo);
    }

    @GetMapping("/lesson/{lessonID}")
    public ResponseEntity<List<Video>> getVideoById(@PathVariable String lessonID) {
        List<Video> videos = videoRepository.findByLesson(lessonID);
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<String> createVideo(@RequestBody Video video) {
        videoRepository.save(video);
        return ResponseEntity.ok("Video was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }
        video.setId(id);
        videoRepository.save(video);
        return ResponseEntity.ok("Video was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        Optional<Video> optVideo = videoRepository.findById(id);

        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }

        Video video = optVideo.get();
        serverMapper.updateVideoFromDto(videoDTO, video);
        videoRepository.save(video);
        return ResponseEntity.ok("Video patched");
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
