package by.felix.softarextest.services.note.impl;

import by.felix.softarextest.dao.NoteDao;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import by.felix.softarextest.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao;

    @Autowired
    public void setNoteCrud(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public List<Note> getAllUserNotes(long userId) throws APPException {
        return noteDao.getAllUserNotes(userId);
    }

    public void saveNote(Note note) {
        noteDao.addOrUpdateNote(note);
    }

    @Override
    public void deleteNote(long noteId, long userId) throws APPException {
        noteDao.deleteNote(noteId, userId);
    }

    @Override
    public Note getNote(long noteId, long userId) throws APPException {
        return noteDao.getNoteByIdAndValidate(noteId, userId);
    }

    @Override
    public void deleteAllUserNotes(long userId) {
        noteDao.deleteAllUserNotes(userId);
    }


}
