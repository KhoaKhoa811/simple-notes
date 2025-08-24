package com.example.notes.service;

import com.example.notes.entity.NoteEntity;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    public NoteEntity create(NoteEntity note) {
        return noteRepository.save(note);
    }

    public NoteEntity getById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public NoteEntity update(Long id, NoteEntity note) {
        NoteEntity existing = getById(id);
        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());
        return noteRepository.save(existing);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
