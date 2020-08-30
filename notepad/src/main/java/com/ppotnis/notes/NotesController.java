package com.ppotnis.notes;

import com.ppotnis.notes.exceptions.NoteNotFoundException;
import com.ppotnis.notes.exceptions.NotesRepoException;
import com.ppotnis.notes.model.NoteBOAndROMapper;
import com.ppotnis.notes.model.NoteRO;
import com.ppotnis.notes.service.NotesService;
import com.ppotnis.notes.service.model.NoteBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller for handling all requests for the notes API.
 */
@RestController
public class NotesController {

	private NotesService service;
	private NoteBOAndROMapper mapper;

	public NotesController(NotesService service, NoteBOAndROMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping("/notes")
	public NoteRO createNote(@RequestBody NoteRO note) throws NotesRepoException{
		NoteBO toCreate = mapper.noteROToNoteBO(note);
		NoteBO created = service.createNote(toCreate);
		return mapper.noteBOToNoteRO(created);
	}

	@GetMapping("/notes/{noteId}")
	public NoteRO getNote(@PathVariable Long noteId) throws NoteNotFoundException, NotesRepoException {
		NoteBO note = service.getNote(noteId);
		return mapper.noteBOToNoteRO(note);
	}

	@RequestMapping("/notes")
	public List<NoteRO> getNotes(@RequestParam(name="userid", required=true) Long userId) throws NotesRepoException{
		List<NoteBO> notes = service.getNotes(userId);
		return notes.stream().map(n -> mapper.noteBOToNoteRO(n)).collect(Collectors.toList());
	}

}
