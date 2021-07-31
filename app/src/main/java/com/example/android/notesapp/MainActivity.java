package com.example.android.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android.notesapp.Activity.InsertNotesActivity;
import com.example.android.notesapp.Adapter.NotesAdapter;
import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;

    TextView noFilter, highToLow, lowToHigh;
    List<Notes> filterNotesAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.notes_recycler_view);
        newNotesBtn = findViewById(R.id.add_notes_btn);

        noFilter = findViewById(R.id.no_filters);
        highToLow = findViewById(R.id.high_to_low);
        lowToHigh = findViewById(R.id.low_to_high);

        noFilter.setBackgroundResource(R.drawable.filter_selected_shape);

        noFilter.setOnClickListener(v -> {
            loadData(0);
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        highToLow.setOnClickListener(v -> {
            loadData(1);
            highToLow.setBackgroundResource(R.drawable.filter_selected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        lowToHigh.setOnClickListener(v -> {
            loadData(2);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });


        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> startActivity(new Intent(this, InsertNotesActivity.class)));


        notesViewModel.getAllNotes.observe(this, notes ->{
            setNotesAdapter(notes);
            filterNotesAllList = notes;});

    }

    private  void   loadData(int i){

        switch (i){

            case 0: notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                        @Override
                        public void onChanged(List<Notes> notes) {
                            setNotesAdapter(notes);
                            filterNotesAllList = notes;
                        }
                    }
            );
            break;

            case 1: notesViewModel.getHighToLow.observe(this, notes -> {
                setNotesAdapter(notes);
                filterNotesAllList = notes;
            }
            );
            break;

            case 2:notesViewModel.getLowToHigh.observe(this, notes -> {
                setNotesAdapter(notes);
                filterNotesAllList = notes;}
            );
            break;
        }
    }

    public void setNotesAdapter(List<Notes> notes){

//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);

        recyclerView.setAdapter(notesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });
        return  true;
    }

    private void notesFilter(String newText) {

        ArrayList<Notes> filterNames = new ArrayList<>();
        newText = newText.toLowerCase();

        Log.e("@@@@","  "+newText);

        for (Notes notes: this.filterNotesAllList){

            String nNotesTitle = notes.notesTitle.toLowerCase();
            String nNotesSubtitle = notes.notesSubtitle.toLowerCase();

            if(nNotesTitle.contains(newText) || nNotesSubtitle.contains(newText)){

                filterNames.add(notes);
            }
        }

        this.notesAdapter.searchNotes(filterNames);
    }
}