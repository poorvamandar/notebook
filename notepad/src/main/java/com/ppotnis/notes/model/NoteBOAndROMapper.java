package com.ppotnis.notes.model;

import com.ppotnis.notes.service.model.NoteBO;
import org.springframework.stereotype.Component;

/**
 * Mapper to map a note from business object to representation object and
 * vice versa
 */
@Component
public class NoteBOAndROMapper {

    public NoteRO noteBOToNoteRO(NoteBO noteBO) {
        return new NoteRO(noteBO.getId(), noteBO.getOwnerId(), noteBO.getTitle(), noteBO.getContent());
    }

    public NoteBO noteROToNoteBO(NoteRO noteRO) {
        return new NoteBO(noteRO.getId(), noteRO.getOwnerId(), noteRO.getTitle(), noteRO.getContent());
    }
}
