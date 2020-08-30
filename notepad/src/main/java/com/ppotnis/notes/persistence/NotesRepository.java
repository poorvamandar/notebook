package com.ppotnis.notes.persistence;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.persistence.model.NoteDO;

import java.util.List;

/**
 * Interface for notes persistence layer
 */
public interface NotesRepository {
    /**
     * Retrieve a note specified by its id from the data store
     * @param noteId
     * @return Note if found
     * @throws NoteNotFoundException
     */
    NoteDO getNote(Long noteId) throws NoteNotFoundException;

    /**
     * Persist the specified note in the data store.
     * @param note
     * @return Created note if successful
     */
    NoteDO createNote(NoteDO note);

    /**
     * Retrieve the list of notes for the specified user from the data store
     * @param ownerId
     * @return List of notes if found
     */
    List<NoteDO> getNotes(Long ownerId);
}
