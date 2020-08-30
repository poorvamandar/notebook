package com.ppotnis.notes.service;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.exceptions.NotesRepoException;
import com.ppotnis.notes.persistence.NotesRepository;
import com.ppotnis.notes.persistence.model.NoteDO;
import com.ppotnis.notes.service.model.NoteBO;
import com.ppotnis.notes.service.model.NoteBOAndDOMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NotesServiceTests {

    @InjectMocks
    private NotesServiceImpl service;

    @Mock
    private NotesRepository mockRepo;

    @Mock
    private NoteBOAndDOMapper mockMapper;

    @Test
    public void createNoteSuccess() {

        NoteBO input = new NoteBO(null, 345L, "title", "content");
        NoteDO inputToDO = new NoteDO(345L, "title", "content");
        NoteDO savedDO = new NoteDO(123L, 345L, "title", "content");
        NoteBO savedToBO = new NoteBO(123L, 345L, "title", "content");

        when(mockMapper.noteBOToNoteDO(input)).thenReturn(inputToDO);
        when(mockRepo.createNote(inputToDO)).thenReturn(savedDO);
        when(mockMapper.noteDOToNoteBO(savedDO)).thenReturn(savedToBO);


        try {
            NoteBO actual = service.createNote(input);
            assertEquals(savedToBO, actual);
        } catch (NotesRepoException e) {
            fail("should not throw NotesRepoException");
        }
    }

    @Test
    public void createNoteWhenRepoException() {
        NoteBO input = new NoteBO(null, 345L, "title", "content");
        NoteDO inputToDO = new NoteDO(345L, "title", "content");
        when(mockMapper.noteBOToNoteDO(input)).thenReturn(inputToDO);
        when(mockRepo.createNote(inputToDO)).thenThrow(new RuntimeException());

        assertThrows(NotesRepoException.class, () -> service.createNote(input));
    }

    @Test
    public void getNoteSuccess() throws NoteNotFoundException {

        NoteDO foundDO = new NoteDO(123L, 345L, "title", "content");
        NoteBO foundToBO = new NoteBO(123L, 345L, "title", "content");

        when(mockRepo.getNote(123L)).thenReturn(foundDO);
        when(mockMapper.noteDOToNoteBO(foundDO)).thenReturn(foundToBO);

        try {
            NoteBO actual = service.getNote(123L);
            assertEquals(foundToBO, actual);
        } catch (Exception e) {
            fail("should not throw any exception");
        }
    }

    @Test
    public void getNoteWhenNoteNotPresent() throws NoteNotFoundException {

        when(mockRepo.getNote(123L)).thenThrow(new NoteNotFoundException("not found"));
        assertThrows(NoteNotFoundException.class, () -> service.getNote(123L));
    }

    // @Test getNotesSuccess
    // @Test getNotesWhenNoNotesForUser
    // @Test getNotesWhenRepoException
}
