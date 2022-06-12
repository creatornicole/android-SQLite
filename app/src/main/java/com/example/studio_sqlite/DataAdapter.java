package com.example.studio_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Adapter for Customized Design of ListView-Items
 *
 * @author Nicole Gottschall
 * @since 2022-06-12
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<String> todos;

    public DataAdapter(ArrayList<String> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        holder.task.setText(todos.get(position));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView task;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
        }
    }
}
