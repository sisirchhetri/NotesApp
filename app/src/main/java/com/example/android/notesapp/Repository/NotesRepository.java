package com.example.android.notesapp.Repository;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.notesapp.Dao.NotesDao;
import com.example.android.notesapp.Database.NotesDatabase;
import com.example.android.notesapp.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;

    public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> getHighToLow;

    public LiveData<List<Notes>> getLowToHigh;

    public NotesRepository(Application application) {

        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);

        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        getHighToLow = notesDao.getHighToLow();
        getLowToHigh = notesDao.getLowToHigh();

    }

    public void insertNotes(Notes... notes) {
        notesDao.insertNotes(notes);
    }

    public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }


}

