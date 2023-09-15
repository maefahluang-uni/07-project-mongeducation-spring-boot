package spring.edu.monglesson.service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    public VideoControllerService(VideoRepository videoRepository, ServerMapper serverMapper) {
        this.videoRepository = videoRepository;
        this.serverMapper = serverMapper;
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
