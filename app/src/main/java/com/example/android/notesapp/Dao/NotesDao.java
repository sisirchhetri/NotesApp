package com.example.android.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.notesapp.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM NOTES_DATABASE")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_priority DESC")
    LiveData<List<Notes>> getHighToLow();

    @Query("SELECT * FROM NOTES_DATABASE ORDER BY notes_priority ASC")
    LiveData<List<Notes>> getLowToHigh();

    @Insert
    void insertNotes(Notes... notes);

    @Update
    void updateNotes(Notes notes);

    @Query("DELETE FROM Notes_Database WHERE id = :id")
    void deleteNotes(int id);

}
