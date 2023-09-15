package spring.edu.monglesson.model;

public class NoteDTO {
    private Long id;
    private String name;
    private String noteSource;
    private String lessonID;

    public NoteDTO() {

    }

    public NoteDTO(Long id, String name, String noteSource, String lessonID) {
        this.id = id;
        this.name = name;
        this.noteSource = noteSource;
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

    public String getNoteSource() {
        return noteSource;
    }

    public void setNoteSource(String noteSource) {
        this.noteSource = noteSource;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

}
