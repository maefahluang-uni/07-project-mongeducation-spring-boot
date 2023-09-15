package spring.edu.monglesson.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.model.Video;
import spring.edu.monglesson.model.VideoDTO;
import spring.edu.monglesson.repository.VideoRepository;

@Service
public class VideoControllerService {
    private final VideoRepository videoRepository;
    private ServerMapper serverMapper;
    LessonControllerService lessonControllerService;

    Logger LOG = LoggerFactory.getLogger(VideoControllerService.class);

    @Autowired
    public VideoControllerService(VideoRepository videoRepository, ServerMapper serverMapper,
            LessonControllerService lessonControllerService) {
        this.videoRepository = videoRepository;
        this.serverMapper = serverMapper;
        this.lessonControllerService = lessonControllerService;
    }

    public List<Video> getAllVideo() {
        List<Video> videos = videoRepository.findAll();
        return videos;
    }

    public Optional<Video> getVideoById(Long id) {
        Optional<Video> optVideo = videoRepository.findById(id);
        return optVideo;
    }

    public List<Video> getVideoByLessonId(String lessonID) {
        List<Video> videos = videoRepository.findByLessonID(lessonID);
        return videos;
    }

    public void saveVideo(Video video) {
        if (video.getLessonID() == null || !lessonControllerService.getLessonById(video.getId()).isPresent()) {
            LOG.info("Can not save. Nust have LessonID");
            return;
        }
        videoRepository.save(video);
    }

    public void mapperVideo(VideoDTO videoDTO, Video video) {
        serverMapper.updateVideoFromDto(videoDTO, video);
        saveVideo(video);
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    public void deleteAllVideo() {
        videoRepository.deleteAll();
    }
}
