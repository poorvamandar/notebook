package com.ppotnis.notes;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import com.ppotnis.notes.model.NoteRO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate template;


	@Test
    public void createNote() throws Exception {
        URL url = new URL("http://localhost:" + port + "/notes");
        NoteRO note = new NoteRO(null, 123L, "title", "content");

        ResponseEntity<NoteRO> response = template.postForEntity(url.toString(), note, NoteRO.class);
        assertThat(response.getBody().getOwnerId()).isEqualTo(123L);
        assertThat(response.getBody().getContent()).isEqualTo("content");
        assertThat(response.getBody().getTitle()).isEqualTo("title");

        // deleteTestNotes(expected.getId());
    }

	@Test
    public void getNotes() throws Exception {
	    NoteRO testNote = createTestNotes(789L, "title", "content");

	    URL url = new URL("http://localhost:" + port + "/notes?userid=789");
        ResponseEntity<NoteRO[]> response = template.getForEntity(url.toString(), NoteRO[].class);

        assertThat(response.getBody().length).isEqualTo(1);
        NoteRO actualNote = response.getBody()[0];
        assertThat(actualNote.getOwnerId()).isEqualTo(testNote.getOwnerId());
        assertThat(actualNote.getTitle()).isEqualTo(testNote.getTitle());
        assertThat(actualNote.getContent()).isEqualTo(testNote.getContent());

        // deleteTestNotes(testNote.getId());
    }

    @Test
    public void getNote() throws Exception {
        NoteRO testNote = createTestNotes(345L, "title", "content");

        URL url = new URL("http://localhost:" + port + "/notes/" + testNote.getId());
        ResponseEntity<NoteRO> response = template.getForEntity(url.toString(), NoteRO.class);

        assertThat(response.getBody()).isEqualTo(testNote);

        // deleteTestNotes(testNote.getId());
    }

    // Helper to create test notes for the integration tests
    private NoteRO createTestNotes(Long userId, String title, String content) throws Exception {
        URL url = new URL("http://localhost:" + port + "/notes");
        NoteRO note = new NoteRO(null, userId, title, content);
        ResponseEntity<NoteRO> response = template.postForEntity(url.toString(), note, NoteRO.class);
        return response.getBody();
    }
}
