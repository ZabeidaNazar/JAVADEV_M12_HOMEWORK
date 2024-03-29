package com.homework.java;

import com.homework.java.dao.NoteDaoForPseudoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class NoteCrudServiceTest {
    private NoteService noteService = new NoteService(new NoteDaoForPseudoDB());

    @Test
    void testListAll() {
        List<Note> notes = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Note note = new Note("Title " + i, "Content");
            notes.add(note);
            noteService.add(note);
        }

        List<Note> resNotes = noteService.listAll();
        Assertions.assertEquals(notes, resNotes);
    }

    @Test
    void testSave() {
        Note note = new Note("Title", "Content");

        noteService.add(note);

        Note resNote = noteService.getById(note.getId());
        Assertions.assertEquals(note, resNote);
    }

    @Test
    void testGetById() {
        Note note = new Note("Title", "Content Content Content");
        noteService.add(note);

        Note actualNote = noteService.getById(note.getId());

        Assertions.assertEquals(note, actualNote);
    }

    @Test
    void testGetByIdNoteWithNotExistingId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.getById(Long.MAX_VALUE));
    }

    @Test
    void testUpdate() {
        Note note = new Note("title", "Content");
        noteService.add(note);

        note.setTitle("Interesting title");

        noteService.update(note);

        Note resNote = noteService.getById(note.getId());
        Assertions.assertEquals(note, resNote);
    }

    @Test
    void testUpdateNoteWithNotExistingId() {
        Note note = new Note(Long.MAX_VALUE, "Title", "Content");

        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.update(note));
    }

    @Test
    void testDelete() {
        Note note = new Note("must be deleted", "Content");
        noteService.add(note);

        noteService.deleteById(note.getId());

        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.getById(note.getId()));
    }

    @Test
    void testDeleteNoteWithNotExistingId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> noteService.deleteById(Long.MAX_VALUE));
    }
}