package com.example.notes_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.Room.NotesTable;

import java.util.ArrayList;
import java.util.List;

public class NotesAdpater extends RecyclerView.Adapter<NotesAdpater.MyViewHolder>{
    private Context context;
    private List<NotesTable> notesTableList;

    private NotesAdapterInterface adapterInterface;

    public NotesAdpater(Context context,NotesAdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
        this.context = context;
        notesTableList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotesTable notesTable = notesTableList.get(position);
        holder.title.setText(notesTable.getTitle());
        holder.description.setText(notesTable.getDescription());
    }

    @Override
    public int getItemCount() {
        return notesTableList.size();
    }

    public void addNotes(NotesTable notesTable) {
        notesTableList.add(notesTable);
        notifyDataSetChanged();
    }
    public void removeNotes(int position) {
        notesTableList.remove(position);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

            // Long Click listener
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    adapterInterface.LongClick(getAdapterPosition(),notesTableList.get(getAdapterPosition()).getId());
                    return true;
                }
            });

            // Simple click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterInterface.SimpleClick(getAdapterPosition(),notesTableList.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
