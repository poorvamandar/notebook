package com.ppotnis.notes.service.model;

import com.ppotnis.notes.persistence.model.NoteDO;
import org.springframework.stereotype.Component;

/**
 * Mapper to map a note from business object and data object and
 * vice versa
 */
@Component
public class NoteBOAndDOMapper {
    public NoteBO noteDOToNoteBO(NoteDO noteDO) {
        return new NoteBO(noteDO.getId(), noteDO.getOwnerId(), noteDO.getTitle(), noteDO.getContent());
    }

    public NoteDO noteBOToNoteDO(NoteBO noteBO) {
        return new NoteDO(noteBO.getOwnerId(), noteBO.getTitle(), noteBO.getContent());
    }
}
