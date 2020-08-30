package com.ppotnis.notes.persistence;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.persistence.model.NoteDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of the notes persistence layer
 */
@Component
public class NotesRepositoryImpl implements NotesRepository {

    private NotesJPARepository jpaRepository;

    public NotesRepositoryImpl(NotesJPARepository repository) {
        this.jpaRepository = repository;
    }

    @Override
    public NoteDO getNote(Long noteId) throws NoteNotFoundException {
        return jpaRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("note not found"));
    }

    public NoteDO createNote(NoteDO note) {
        return jpaRepository.save(note);
    }

    public List<NoteDO> getNotes(Long ownerId) {
        return jpaRepository.findByOwnerId(ownerId);
    }
}
