package com.example.android.notesapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;

    }

    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }

    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }

    public void deleteNote (int id){
        repository.deleteNotes(id);
    }


}
