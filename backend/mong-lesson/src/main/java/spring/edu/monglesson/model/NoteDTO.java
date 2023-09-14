package spring.edu.monglesson.model;

public class NoteDTO {
    private Long id;
    private String name;
    private Lesson lesson;

    public NoteDTO() {

    }

    public NoteDTO(Long id, String name, Lesson lesson) {
        this.id = id;
        this.name = name;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}
