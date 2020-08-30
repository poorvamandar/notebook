package com.ppotnis.notes.persistence.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Note data object
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class NoteDO {
    private @Id @GeneratedValue(strategy= GenerationType.AUTO) Long id;
    private Long ownerId;
    private String title;
    private String content;

    public NoteDO(Long ownerId, String title, String content) {
        this.ownerId = ownerId;
        this.title = title;
        this.content = content;
    }

    protected NoteDO() {}
}
