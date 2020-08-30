package com.ppotnis.notes.service;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.exceptions.NotesRepoException;
import com.ppotnis.notes.persistence.NotesRepository;
import com.ppotnis.notes.persistence.model.NoteDO;
import com.ppotnis.notes.service.model.NoteBO;
import com.ppotnis.notes.service.model.NoteBOAndDOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation for the notes service layer.
 */
@Component
public class NotesServiceImpl implements NotesService {

    private NotesRepository notesRepository;
    private NoteBOAndDOMapper mapper;

    public NotesServiceImpl(NotesRepository notesRepository, NoteBOAndDOMapper mapper) {
        this.notesRepository = notesRepository;
        this.mapper = mapper;
    }

    @Override
    public NoteBO getNote(Long noteId) throws NoteNotFoundException, NotesRepoException {
        try {
            NoteDO noteDO = notesRepository.getNote(noteId);
            return mapper.noteDOToNoteBO(noteDO);
        } catch (NoteNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new NotesRepoException("exception from repository during getNote " + e);
        }
    }

    @Override
    public NoteBO createNote(NoteBO note) throws NotesRepoException {
        try {
            NoteDO noteDO = mapper.noteBOToNoteDO(note);
            NoteDO saved = notesRepository.createNote(noteDO);
            return mapper.noteDOToNoteBO(saved);
        } catch (Exception e) {
            throw new NotesRepoException("exception from repository during createNote " + e);
        }
    }

    @Override
    public List<NoteBO> getNotes(Long requestingUserId) throws NotesRepoException {
        try {
            List<NoteDO> notes = notesRepository.getNotes(requestingUserId);
            return notes.stream().map(n -> mapper.noteDOToNoteBO(n)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotesRepoException("exception from repository during getNotes " + e);
        }
    }
}
