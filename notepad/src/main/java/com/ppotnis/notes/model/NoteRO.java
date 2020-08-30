package com.ppotnis.notes.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Representation object for a note.
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class NoteRO {
    private Long id;
    private Long ownerId;
    private String title;
    private String content;
}
