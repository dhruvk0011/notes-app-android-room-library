package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notes_app.Room.DbInterface;
import com.example.notes_app.Room.NotesDatabase;
import com.example.notes_app.Room.NotesTable;
import com.example.notes_app.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    NotesDatabase notesDatabase;
    DbInterface dbInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int id = intent.getIntExtra("id",-1);

        binding.titleNote.setText(title);
        binding.descNote.setText(description);

        notesDatabase = NotesDatabase.getInstance(this);
        dbInterface = notesDatabase.getInterface();

        binding.updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesTable notesTable = new NotesTable();
                notesTable.setId(id);
                notesTable.setTitle(binding.titleNote.getText().toString());
                notesTable.setDescription(binding.descNote.getText().toString());
                dbInterface.updateNotes(notesTable);
                finish();
                Toast.makeText(UpdateActivity.this, "Updated !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}