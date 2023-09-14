package spring.edu.monglesson.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
import spring.edu.monglesson.model.Note;
import spring.edu.monglesson.model.NoteDTO;
import spring.edu.monglesson.repository.NoteRepository;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteRepository noteRepository;
    private KafkaTemplate<String, Note> kafkaTemplate;

    private ServerMapper serverMapper;

    @Autowired
    public NoteController(NoteRepository noteRepository, KafkaTemplate<String, Note> kafkaTemplate,
            ServerMapper serverMapper) {
        this.noteRepository = noteRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> Notes = noteRepository.findAll();
        return ResponseEntity.ok(Notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getNoteById(@PathVariable Long id) {
        Optional<Note> optNote = noteRepository.findById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Note is found\n" + optNote);
    }

    @GetMapping("/{lesson}")
    public ResponseEntity<String> getNoteById(@PathVariable Lesson lesson) {
        List<Note> optNote = noteRepository.findByLesson(lesson);
        return ResponseEntity.status(HttpStatus.OK).body("Note in " + lesson + "\n" + optNote);
    }

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody Note Note) {
        noteRepository.save(Note);
        return ResponseEntity.ok("Note was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNote(@PathVariable Long id, @RequestBody Note Note) {
        Optional<Note> optNote = noteRepository.findById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        Note.setId(id);
        noteRepository.save(Note);
        return ResponseEntity.ok("Note was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody NoteDTO NoteDTO) {
        Optional<Note> optNote = noteRepository.findById(id);

        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }

        Note Note = optNote.get();
        serverMapper.updateNoteFromDto(NoteDTO, Note);
        noteRepository.save(Note);

        return ResponseEntity.ok("Note patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.ok("Note was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllNote() {
        noteRepository.deleteAll();
        return ResponseEntity.ok("Note was Deleted");
    }

}
