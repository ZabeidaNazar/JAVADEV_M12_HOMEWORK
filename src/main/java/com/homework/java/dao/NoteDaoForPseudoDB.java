package com.homework.java.dao;

import com.homework.java.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteDaoForPseudoDB implements NoteDao {
    private Map<Long, Note> db = new LinkedHashMap<>();
    private long autoIncrement = 1;

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Note add(Note note) {
        long id = autoIncrement++;
        note.setId(id);
        db.put(id, note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if (db.remove(id) == null) {
            throw new IllegalArgumentException("Нотатки з id " + id + " не знайдено!");
        }
    }

    @Override
    public void update(Note note) {
        if (db.replace(note.getId(), note) == null) {
            throw new IllegalArgumentException("Нотатки з id " + note.getId() + " не знайдено!");
        }
    }

    @Override
    public Note getById(long id) {
        Note note = db.get(id);
        if (note == null) {
            throw new IllegalArgumentException("Нотатки з id " + id + " не знайдено!");
        }
        return note;
    }
}
