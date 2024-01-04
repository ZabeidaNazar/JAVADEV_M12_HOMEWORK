package com.homework.java;

import com.homework.java.dao.NoteDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteDao noteDao;
    public List<Note> listAll() {
        return noteDao.listAll();
    }
    public Note add(Note note) {
        return noteDao.add(note);
    }
    public void deleteById(long id) {
        noteDao.deleteById(id);
    }
    public void update(Note note) {
        noteDao.update(note);
    }
    public Note getById(long id) {
        return noteDao.getById(id);
    }
}
