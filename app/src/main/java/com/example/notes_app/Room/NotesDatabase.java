package com.example.notes_app.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotesTable.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract DbInterface getInterface();
    public static volatile NotesDatabase INSTANCE;

    public static NotesDatabase getInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,NotesDatabase.class,"notes").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
