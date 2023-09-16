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

import spring.edu.monglesson.model.Note;
import spring.edu.monglesson.model.NoteDTO;
import spring.edu.monglesson.service.NoteControllerService;


@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteControllerService noteControllerService;

    @Autowired
    public NoteController(NoteControllerService NoteControllerService) {
        this.noteControllerService = NoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteControllerService.getAllNote();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Note>> getNoteById(@PathVariable Long id) {
        Optional<Note> optNote = noteControllerService.getNoteById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(optNote);
    }

    @GetMapping("/lesson/{lessonID}")
    public ResponseEntity<List<Note>> getNoteById(@PathVariable String lessonID) {
        List<Note> notes = noteControllerService.getNoteByLessonId(lessonID);
        return ResponseEntity.ok(notes);
    }
    

    @PostMapping
    public ResponseEntity<String> postNote(@RequestBody Note note) {
        noteControllerService.saveNote(note);
        return ResponseEntity.ok("Note was created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> optNote = noteControllerService.getNoteById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        noteControllerService.saveNote(note);
        return ResponseEntity.ok("Note was updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Optional<Note> optNote = noteControllerService.getNoteById(id);
        if (!optNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
        }
        noteControllerService.mapperNote(noteDTO, optNote.get());
        return ResponseEntity.ok("Note patched.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        noteControllerService.deleteNote(id);
        return ResponseEntity.ok("Note was Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllNote() {
        noteControllerService.deleteAllNote();
        return ResponseEntity.ok("Note was Deleted");
    }
}
