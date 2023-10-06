package spring.edu.monglesson.model;

public class LessonDTO {
    private Long id;

    private String name;

    private String courseID;

    public LessonDTO() {
    }

    public LessonDTO(Long id, String name, String courseID) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
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

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

}
