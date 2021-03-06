package by.felix.softarextest.services.note.impl;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import by.felix.softarextest.repository.NoteRepository;
import by.felix.softarextest.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllUserNotes(long userId) throws APPException {
        return noteRepository.getAllUserNotes(userId).orElseThrow(() ->
                new APPException("User %s doest not have any notes", userId));
    }

    @Override
    public void saveNote(Note note) {
        noteRepository.saveAndFlush(note);
    }

    @Override
    public void deleteNote(long noteId, long userId) throws APPException {
        Note note = noteRepository.getOne(noteId);
        if (note.getUserId().getId() != userId) throw new APPException("User %s does not have rights on note %2d", userId, noteId);
        noteRepository.deleteById(noteId);
    }

    @Override
    public Note getNote(long noteId, long userId) throws APPException {
        var node = noteRepository.findById(noteId).orElseThrow(() ->
                new APPException("No node found (NodeID = %s)", noteId));
        if (node.getUserId().getId() != userId) throw new APPException("Node %s does not belong to User %2d", noteId, userId);
        return node;
    }

    @Override
    public void deleteAllUserNotes(long userId) {
        noteRepository.deleteAllByUserId(userId);
    }


}
