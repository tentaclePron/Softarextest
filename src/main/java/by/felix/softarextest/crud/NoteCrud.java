package by.felix.softarextest.crud;


import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;

import java.util.List;

public interface NoteCrud {

    Note addNote(Note note);
    void deleteNote(long id);
    Note getNoteByIdAndValidate(long noteId, long userId) throws APPException;
    Note getNoteById(Long noteId) throws APPException;
    Note updateNote(Note note);
    List<Note> getAllUserNotes(long userId) throws APPException;
}
