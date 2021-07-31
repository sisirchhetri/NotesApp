package com.example.android.notesapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.notesapp.Activity.UpdateNotesActivity;
import com.example.android.notesapp.MainActivity;
import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesItem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesItem = new ArrayList<>(notes);
    }

    public  void  searchNotes( List<Notes> filteredNotes){
        this.notes = filteredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull NotesViewHolder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;

        }

//        if(note.notesPriority.equals("1")) holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
//        if(note.notesPriority.equals("2")) holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
//        if(note.notesPriority.equals("3")) holder.notesPriority.setBackgroundResource(R.drawable.red_shape);


        holder.notesTitle.setText(note.notesTitle);
        holder.notesSubtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.notesSubtitle);
            intent.putExtra("priority",note.notesPriority);
            intent.putExtra("notes",note.notes);

            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

     static class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView notesTitle, notesSubtitle, notesDate;
        View notesPriority;

        public NotesViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            notesTitle = itemView.findViewById(R.id.notes_title);
            notesSubtitle = itemView.findViewById(R.id.notes_subtitle);
            notesDate = itemView.findViewById(R.id.notes_date);
            notesPriority = itemView.findViewById(R.id.notes_priority);
        }
    }
}


