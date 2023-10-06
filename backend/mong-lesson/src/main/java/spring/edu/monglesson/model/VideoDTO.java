package spring.edu.monglesson.model;

import java.sql.Time;

public class VideoDTO {
    private Long id;
    private String name;
    private Time time;
    private String videoSource;
    private String lessonID;

    public VideoDTO() {
    }

    public VideoDTO(Long id, String name, Time time, String videoSource, String lessonID) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.videoSource = videoSource;
        this.lessonID = lessonID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

}
