package com.example.android.notesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notesapp.Activity.InsertNotesActivity;
import com.example.android.notesapp.Adapter.NotesAdapter;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.notes_recycler_view);
        newNotesBtn = findViewById(R.id.add_notes_btn);


        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, InsertNotesActivity.class));
        });


        notesViewModel.getAllNotes.observe(this, notes -> {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            notesAdapter = new NotesAdapter(MainActivity.this, notes);

            recyclerView.setAdapter(notesAdapter);

        });
    }
}