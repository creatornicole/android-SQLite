package com.example.studio_sqlite;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //References to buttons and other controls on the layout
    private Button btn_add;
    private EditText et_todo;
    private Switch sw;
    private RecyclerView rv;
    private DataAdapter todoAdapter;
    private DataBaseHelper dbHelper;

    /**
     * Initialization Method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataBaseHelper(MainActivity.this);

        assignVariables();

        rv.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new DataAdapter();
        rv.setAdapter(todoAdapter);

        registerClick();
        showAllToDos(dbHelper);
    }

    private void assignVariables() {
        //assign values to variables
        btn_add = findViewById(R.id.btn_add);
        et_todo = findViewById(R.id.et_todo);
        rv = findViewById(R.id.rv);
    }

    private void showAllToDos(DataBaseHelper dbHelper) {
        //TODO: Update RecyclerView
    }

    private void registerClick() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDoTitle = et_todo.getText().toString();

                if(Pattern.matches("s*", toDoTitle)) {
                    Toast.makeText(MainActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();
                } else if(dbHelper.existsInDB(new DataModel(toDoTitle))) {
                    Toast.makeText(MainActivity.this, "Already added as ToDo", Toast.LENGTH_SHORT).show();
                } else {
                    DataModel dModel = new DataModel(toDoTitle);
                    dbHelper.addOne(dModel);

                    showAllToDos(dbHelper);
                }
                //empty input field
                et_todo.setText("");
            }
        });
    }
}