package com.Namekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Namekart.entity.NoteEntity;
import com.Namekart.repository.NoteRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allow React

public class SessionController {
		
		@Autowired
		NoteRepository noteRepository;
		
		@GetMapping("/notes")
		public ResponseEntity<List<NoteEntity>> readNotes () {
			return new ResponseEntity<List<NoteEntity>>(noteRepository.findAll(), HttpStatus.OK);
		}
		
		@PostMapping("/notes")
		public ResponseEntity<NoteEntity> createNote(@RequestBody NoteEntity note) {
			return new ResponseEntity<NoteEntity>(noteRepository.save(note), HttpStatus.CREATED);
		}
		
		@GetMapping("/notes/{noteId}")
		public ResponseEntity<NoteEntity> readNote (@PathVariable Integer noteId) {
			return new ResponseEntity<NoteEntity>(noteRepository.findById(noteId).get(), HttpStatus.OK);
		}
		
		@DeleteMapping("/notes/{noteId}")
		public ResponseEntity<HttpStatus> deleteNote (@PathVariable Integer noteId) {
			noteRepository.deleteById(noteId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		
		@PutMapping("/notes/{noteId}")
		public ResponseEntity<NoteEntity> updateNote (@PathVariable Integer noteId, @RequestBody NoteEntity note) {
			
			note.setNoteId(noteId);
			return new ResponseEntity<NoteEntity>(noteRepository.save(note), HttpStatus.OK);
		}
	}





