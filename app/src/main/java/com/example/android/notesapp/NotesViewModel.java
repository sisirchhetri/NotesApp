package com.example.android.notesapp;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;

    }

    void insertNote(Notes notes){
        repository.insertNotes(notes);
    }

    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }

    public void deleteNote (int id){
        repository.deleteNotes(id);
    }
}
