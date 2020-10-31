package by.felix.softarextest.controllers.nodes;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;

import java.util.List;

public interface NoteApi {
    public List<Note> getNotes() throws APPException;

    public Note saveNote(Note note);

    public void deleteNote(long noteId);
}
