package spring.edu.monglesson.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.model.Note;
import spring.edu.monglesson.model.NoteDTO;
import spring.edu.monglesson.repository.NoteRepository;

@Service
public class NoteControllerService {
    private final NoteRepository noteRepository;
    private ServerMapper serverMapper;
    LessonControllerService lessonControllerService;

    Logger LOG = LoggerFactory.getLogger(NoteControllerService.class);

    @Autowired
    public NoteControllerService(NoteRepository noteRepository, ServerMapper serverMapper,
            LessonControllerService lessonControllerService) {
        this.noteRepository = noteRepository;
        this.serverMapper = serverMapper;
        this.lessonControllerService = lessonControllerService;
    }

    public List<Note> getAllNote() {
        List<Note> notes = noteRepository.findAll();
        return notes;
    }

    public Optional<Note> getNoteById(Long id) {
        Optional<Note> optnote = noteRepository.findById(id);
        return optnote;
    }

    public List<Note> getNoteByLessonId(String lessonID) {
        List<Note> notes = noteRepository.findByLessonID(lessonID);
        return notes;
    }

    public void saveNote(Note note) {
        if (note.getLessonID() == null || !lessonControllerService.getLessonById(Long.parseLong(note.getLessonID())).isPresent()) {
            LOG.info("Can not save. Nust have LessonID");
            return;
        }
        noteRepository.save(note);
    }

    public void mapperNote(NoteDTO noteDTO, Note note) {
        serverMapper.updateNoteFromDto(noteDTO, note);
        saveNote(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public void deleteAllNote() {
        noteRepository.deleteAll();
    }

    public void saveLesson(Note lesson) {
    }
}
