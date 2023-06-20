package com.example.notes_app.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DbInterface {
    @Insert
    void createNote(NotesTable notesTable);

    @Update
    void updateNotes(NotesTable notesTable);

    @Query("delete from notes where id=:id")
    void deleteNote(int id);

    @Query("select * from notes")
    List<NotesTable> getAllNotes();
}