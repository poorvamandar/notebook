package com.ppotnis.notes.service.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Business object for a note
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class NoteBO {
    private Long id;
    private Long ownerId;
    private String title;
    private String content;
}
