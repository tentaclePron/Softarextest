package by.felix.softarextest.controllers.nodes;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteApiController implements NoteApi {

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    @GetMapping("/userNotes")
    public List<Note> getAllUserNotes(Authentication authentication) throws APPException {
        User user = (User) authentication.getPrincipal();
        return noteService.getAllUserNotes(user.getId());
    }

    @Override
    public Note getNote(long noteId, Authentication authentication) throws APPException {
        User user = (User) authentication.getPrincipal();
        return noteService.getNote(noteId, user.getId());
    }

    @Override
    @PostMapping("/saveNote")
    public String saveNote(@RequestBody Note note, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        note.setUserId(user);
        noteService.saveNote(note);
        return "OK";
    }

    @Override
    @PostMapping("/deleteNote")
    public void deleteNote(long noteId, Authentication authentication) throws APPException {
        User user = (User) authentication.getPrincipal();
        noteService.deleteNote(noteId, user.getId());
    }

    @Override
    public void deleteAllNotes(long userId) {
        noteService.deleteAllUserNotes(userId);
    }
}
