package com.example.android.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM NOTES_DATABASE")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Update
    void updateNotes(Notes notes);

    @Query("DELETE FROM Notes_Database WHERE id = :id")
    void deleteNotes(int id);

}
