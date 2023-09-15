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

import spring.edu.monglesson.ServerMapper;
import spring.edu.monglesson.kafka.KafkaService;
import spring.edu.monglesson.model.Note;
import spring.edu.monglesson.model.NoteDTO;
import spring.edu.monglesson.repository.NoteRepository;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteRepository noteRepository;
    private KafkaService kafkaService;
    private ServerMapper serverMapper;

    @Autowired
    public NoteController(NoteRepository noteRepository, KafkaService kafkaService, ServerMapper serverMapper) {
        this.noteRepository = noteRepository;
        this.kafkaService = kafkaService;
        this.serverMapper = serverMapper;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Note>> getNoteById(@PathVariable Long id) {
        Optional<Note> optNote = noteRepository.findById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optNote);
    }

    @GetMapping("/lesson/{lessonID}")
    public ResponseEntity<List<Note>> getNoteById(@PathVariable String lessonID) {
        List<Note> notes = noteRepository.findByLesson(lessonID);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody Note note) {
        noteRepository.save(note);
        return ResponseEntity.ok("Note was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> optNote = noteRepository.findById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        note.setId(id);
        noteRepository.save(note);
        return ResponseEntity.ok("Note was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStudent(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Optional<Note> optNote = noteRepository.findById(id);

        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }

        Note note = optNote.get();
        serverMapper.updateNoteFromDto(noteDTO, note);
        noteRepository.save(note);
        return ResponseEntity.ok("Note patched");
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
