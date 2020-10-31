package by.felix.softarextest.services.note;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllUserNotes(long userId) throws APPException;
    void saveNote(Note note);
    void deleteNote(long noteId, long userId) throws APPException;
    Note getNote(long noteId, long userId) throws APPException;
    void deleteAllUserNotes(long userId);
}
