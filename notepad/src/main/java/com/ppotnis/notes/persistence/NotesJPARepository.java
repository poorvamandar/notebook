package com.ppotnis.notes.persistence;

import com.ppotnis.notes.persistence.model.NoteDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Simple CRUD repo for notes.
 */
@Component
public interface NotesJPARepository extends JpaRepository<NoteDO, Long> {

    List<NoteDO> findByOwnerId(Long ownerId);

}
