package com.example.android.notesapp.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.R;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.example.android.notesapp.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";
    int id;
    String title, subtitle, notes;

    //  enable the back function to the button on press
    //  called when overridden onOptionsItemSelected(item) returns false;
//    @Override
//    public boolean onSupportNavigateUp() {
//        Log.v("Back Button", "Inside onSupportNavigation");
//        finish();
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        //assert actionBar != null; or  assert getSupportActionBar() != null;   //null check
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }


        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        notes = getIntent().getStringExtra("notes");
        priority = getIntent().getStringExtra("priority");

        binding.updateTitle.setText(title);
        binding.updateSubtitle.setText(subtitle);
        binding.updateNotes.setText(notes);

        switch (priority) {
            case "1":
                binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
                break;
            case "2":
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                break;
            case "3":
                binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
                break;

        }


        binding.greenPriority.setOnClickListener(v -> {
            priority = "1";
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

        });

        binding.yellowPriority.setOnClickListener(v -> {
            priority = "2";
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
        });

        binding.redPriority.setOnClickListener(v -> {
            priority = "3";
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.greenPriority.setImageResource(0);
        });

        binding.updateNotesBtn.setOnClickListener(v -> {

            title = binding.updateTitle.getText().toString();
            subtitle = binding.updateSubtitle.getText().toString();
            notes = binding.updateNotes.getText().toString();

            updateNotes(title, subtitle, notes);

        });

    }

    private void updateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNote = new Notes();
        updateNote.id = id;
        updateNote.notesTitle = title;
        updateNote.notesSubtitle = subtitle;
        updateNote.notes = notes;
        updateNote.notesDate = sequence.toString();
        updateNote.notesPriority = priority;

        notesViewModel.updateNote(updateNote);
        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Attaching the delete menu list  on the update layout
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    /**
     * Handles click event of the Menu Items
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.delete_item) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_bottom_sheet,
                    (LinearLayout) findViewById(R.id.bottom_sheet));

            bottomSheetDialog.setContentView(view);

            TextView yes = view.findViewById(R.id.delete_yes);
            TextView no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v -> {

                notesViewModel.deleteNote(id);
                Toast.makeText(UpdateNotesActivity.this, "Notes Deleted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            });

            no.setOnClickListener(v -> bottomSheetDialog.dismiss());

            bottomSheetDialog.show();

            return true;
        }

        //back/up button click (also the home id given to it by def )
        else if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }

        else {

           return super.onOptionsItemSelected(item);
//            boolean Return false to allow normal menu processing to proceed (back button also)
//            true to consume it here.
        }

    }
}
