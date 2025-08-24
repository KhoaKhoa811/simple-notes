package com.example.notes.controller;

import com.example.notes.entity.NoteEntity;
import com.example.notes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteEntity> getAll() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public NoteEntity create(@RequestBody NoteEntity note) {
        return noteService.create(note);
    }

    @GetMapping("/{id}")
    public NoteEntity getOne(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @PutMapping("/{id}")
    public NoteEntity update(@PathVariable Long id, @RequestBody NoteEntity note) {
        return noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}

