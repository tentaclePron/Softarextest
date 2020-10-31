package by.felix.softarextest.crud.impl;

import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.Note;
import by.felix.softarextest.repository.NoteRepository;
import by.felix.softarextest.crud.NoteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteCrudImpl implements NoteCrud {

    private NoteRepository noteRepository;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(Note note) {
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public void deleteNote(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note getNoteByIdAndValidate(long noteId, long userId) throws APPException {
        var node = noteRepository.findById(noteId).orElseThrow(() ->
                new APPException("No node found (NodeID = %s)", noteId));
        if (node.getUserId() != userId) throw new APPException("Node %s does not belong to User %2d", noteId, userId);
        return node;
    }

    /**
     * Test only
     * @param noteId
     * @return
     * @throws APPException
     */
    @Override
    public Note getNoteById(Long noteId) throws APPException {
        return noteRepository.findById(noteId).orElseThrow(() ->
                new APPException("No node found (NodeID = %s)", noteId));
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public List<Note> getAllUserNotes(long userId) throws APPException {
        return noteRepository.getAllUserNotes(userId).orElseThrow(() -> new APPException("User %s doest not have any notes", userId));
    }
}
