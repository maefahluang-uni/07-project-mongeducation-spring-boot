package spring.edu.monglesson.model;

import java.sql.Time;

public class VideoDTO {
    private Long id;
    private String name;
    private Time time;
    private String videoSource;
    private Lesson lesson;

    public VideoDTO() {
    }

    public VideoDTO(Long id, String name, Time time, String videoSource, Lesson lesson) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.videoSource = videoSource;
        this.lesson = lesson;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}
