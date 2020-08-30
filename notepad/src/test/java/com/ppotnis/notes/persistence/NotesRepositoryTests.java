package com.ppotnis.notes.persistence;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.persistence.model.NoteDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NotesRepositoryTests {

    @InjectMocks
    private NotesRepositoryImpl notesRepository;

    @Mock
    private NotesJPARepository mockJPARepository;

    @Test
    public void createNoteSuccess() {

        NoteDO toSave = new NoteDO(345L, "title", "content");
        NoteDO saved = new NoteDO(123L, 345L, "title", "content");
        when(mockJPARepository.save(toSave)).thenReturn(saved);

        NoteDO actual = notesRepository.createNote(toSave);
        assertEquals(saved, actual);
    }

    @Test
    public void getNoteTestSuccess() {

        NoteDO found = new NoteDO(123L, 345L, "title", "content");
        when(mockJPARepository.findById(123L)).thenReturn(Optional.of(found));

        try {
            NoteDO actual = notesRepository.getNote(123L);
            assertEquals(found, actual);
        } catch (NoteNotFoundException e) {
            fail("should not throw NoteNotFoundException");
        }
    }

    @Test
    public void getNoteTestWhenNoNote() {

        when(mockJPARepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(NoteNotFoundException.class, () -> notesRepository.getNote(123L));
    }

    @Test
    public void getNotesTestSuccess() {

        NoteDO note1 = new NoteDO(1L, 345L, "title", "content");
        NoteDO note2 = new NoteDO(2L, 345L, "new title", "content");
        List<NoteDO> found = Arrays.asList(note1, note2);
        when(mockJPARepository.findByOwnerId(345L)).thenReturn(found);

        List<NoteDO> actual = notesRepository.getNotes(345L);
        assertEquals(2, actual.size());
        assertEquals(note1, actual.get(0));
        assertEquals(note2, actual.get(1));
    }

    @Test
    public void getNotesTestWhenNoNotesForUser() {

        List<NoteDO> empty = Arrays.asList();
        when(mockJPARepository.findByOwnerId(345L)).thenReturn(empty);

        List<NoteDO> actual = notesRepository.getNotes(345L);
        assertEquals(0, actual.size());
    }
}
