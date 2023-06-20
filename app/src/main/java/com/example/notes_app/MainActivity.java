package com.example.notes_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notes_app.Room.DbInterface;
import com.example.notes_app.Room.NotesDatabase;
import com.example.notes_app.Room.NotesTable;
import com.example.notes_app.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapterInterface{
    ActivityMainBinding binding;
    NotesDatabase notesDatabase;
    DbInterface  dbInterface;
    List<NotesTable> notesTableList;
    NotesAdpater notesAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesDatabase = NotesDatabase.getInstance(this);
        dbInterface = notesDatabase.getInterface();
        notesTableList = new ArrayList<>();

        binding.addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        notesTableList = dbInterface.getAllNotes();
        notesAdpater = new NotesAdpater(this,this);

        for(int i=0; i<notesTableList.size(); i++) {
            notesAdpater.addNotes(notesTableList.get(i));
        }
        notesAdpater.notifyDataSetChanged();
        binding.notesViewer.setAdapter(notesAdpater);
        binding.notesViewer.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void LongClick(int position, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete").setMessage("Do you want to delete this note ?!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notesAdpater.removeNotes(position);
                        dbInterface.deleteNote(id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        builder.show();
    }

    @Override
    public void SimpleClick(int position, int id) {
        Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("title",notesTableList.get(position).getTitle());
        intent.putExtra("description",notesTableList.get(position).getDescription());
        intent.putExtra("id",notesTableList.get(position).getId());
        startActivity(intent);
    }
}