package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notes_app.Room.DbInterface;
import com.example.notes_app.Room.NotesDatabase;
import com.example.notes_app.Room.NotesTable;
import com.example.notes_app.databinding.ActivityAddNotesBinding;

public class AddNotesActivity extends AppCompatActivity {
    ActivityAddNotesBinding binding;
    NotesDatabase db;
    DbInterface inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = NotesDatabase.getInstance(this);
        inf = db.getInterface();

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.titleNote.getText().toString();
                String description = binding.descNote.getText().toString();
                if(title.trim().length() == 0 && description.trim().length() == 0) {
                    Toast.makeText(AddNotesActivity.this,"Fields are empty!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                NotesTable notesTable = new NotesTable();
                notesTable.setTitle(title);
                notesTable.setDescription(description);

                inf.createNote(notesTable);
                finish();
                Toast.makeText(AddNotesActivity.this, "Saved!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}