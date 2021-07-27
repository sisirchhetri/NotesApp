package com.example.android.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context){

        if(INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context,NotesDatabase.class , "Notes_Database").build();
        }

        return INSTANCE;
    }


}
