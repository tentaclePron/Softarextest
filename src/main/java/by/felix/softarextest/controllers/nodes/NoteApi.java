package by.felix.softarextest.controllers.nodes;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteApi {
    List<Note> getAllUserNotes(Authentication authentication) throws APPException;

    Note getNote(long noteId, Authentication authentication) throws APPException;

    String saveNote(Note note, Authentication authentication);

    void deleteNote(long noteId, Authentication authentication) throws APPException;

    void deleteAllNotes(long userId);
}
