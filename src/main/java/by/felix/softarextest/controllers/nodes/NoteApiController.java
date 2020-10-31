package by.felix.softarextest.controllers.nodes;

import by.felix.softarextest.crud.NoteCrud;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/note")
public class NoteApiController implements NoteApi {

    private NoteCrud noteCrud;

    @Autowired
    public void setNoteCrud(NoteCrud noteCrud) {
        this.noteCrud = noteCrud;
    }

    @Override
    @GetMapping("/userNotes")
    public List<Note> getNotes() throws APPException {
        return noteCrud.getAllUserNotes(1);  //TODO get userid from token
    }

    @Override
    @PostMapping("/saveNote")
    public Note saveNote(@RequestBody Note note) {
        return noteCrud.addNote(note);
    }

    @Override
    @PostMapping("/deleteNote")
    public void deleteNote(long noteId) {
        noteCrud.deleteNote(noteId);
    }
}
