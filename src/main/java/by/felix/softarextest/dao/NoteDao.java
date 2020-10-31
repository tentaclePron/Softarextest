package by.felix.softarextest.dao;


import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;

import java.util.List;

public interface NoteDao {

    Note addOrUpdateNote(Note note);
    void deleteNote(long id, long userId) throws APPException;
    Note getNoteByIdAndValidate(long noteId, long userId) throws APPException;
    List<Note> getAllUserNotes(long userId) throws APPException;
    void deleteAllUserNotes(long userId);
}
