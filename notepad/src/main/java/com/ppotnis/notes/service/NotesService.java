package com.ppotnis.notes.service;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.exceptions.NotesRepoException;
import com.ppotnis.notes.service.model.NoteBO;

import java.util.List;

/**
 * Interface for the service layer for notes
 */
public interface NotesService {
    /**
     * Get a note given its id.
     * @param noteId
     * @return Note if found
     * @throws NoteNotFoundException
     * @throws NotesRepoException
     */
    NoteBO getNote(Long noteId) throws NoteNotFoundException, NotesRepoException;

    /**
     * Create the specified note
     * @param note
     * @return Note if created successfully
     * @throws NotesRepoException
     */
    NoteBO createNote(NoteBO note) throws NotesRepoException;

    /**
     * Get a list of notes for the given user
     * @param userId
     * @return List of notes if found
     * @throws NotesRepoException
     */
    List<NoteBO> getNotes(Long userId) throws NotesRepoException;
}
