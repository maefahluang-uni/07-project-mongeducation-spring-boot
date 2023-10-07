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

import spring.edu.monglesson.model.Video;
import spring.edu.monglesson.model.VideoDTO;
import spring.edu.monglesson.service.VideoControllerService;

@RestController
@RequestMapping("/videoes")
public class VideoController {
    private final VideoControllerService videoControllerService;

    @Autowired
    public VideoController(VideoControllerService VideoControllerService) {
        this.videoControllerService = VideoControllerService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoControllerService.getAllVideo();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Video>> getVideoById(@PathVariable Long id) {
        Optional<Video> optVideo = videoControllerService.getVideoById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optVideo);
    }

    @GetMapping("/lesson/{lessonID}")
    public ResponseEntity<List<Video>> getVideoById(@PathVariable String lessonID) {
        List<Video> videos = videoControllerService.getVideoByLessonId(lessonID);
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<String> postVideo(@RequestBody Video video) {
        videoControllerService.saveVideo(video);
        return ResponseEntity.ok("Video was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putVideo(@PathVariable Long id, @RequestBody Video video) {
        Optional<Video> optVideo = videoControllerService.getVideoById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }
        video.setId(id);
        videoControllerService.saveVideo(video);
        return ResponseEntity.ok("Video was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        Optional<Video> optVideo = videoControllerService.getVideoById(id);
        if (!optVideo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video not found");
        }
        videoControllerService.mapperVideo(videoDTO, optVideo.get());
        return ResponseEntity.ok("Video patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoControllerService.deleteVideo(id);
        return ResponseEntity.ok("Video was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllVideo() {
        videoControllerService.deleteAllVideo();
        return ResponseEntity.ok("Video was Deleted");
    }
}
