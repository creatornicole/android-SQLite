package com.example.studio_sqlite;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

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
    private EditText et_todo, et_descrip;
    private Switch sw;
    private ListView lv;
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
        registerClick();
        showAllToDos(dbHelper);
    }

    private void assignVariables() {
        //assign values to variables
        btn_add = (Button) findViewById(R.id.btn_add);
        et_todo = (EditText) findViewById(R.id.et_todo);
        et_descrip = (EditText) findViewById(R.id.et_descrip);
        sw = (Switch) findViewById(R.id.sw);
        lv = (ListView) findViewById(R.id.lv);
    }

    private void showAllToDos(DataBaseHelper dbHelper) {
        todoAdapter = new DataAdapter(MainActivity.this, R.layout.list_item, dbHelper.getAllAsList());
        lv.setAdapter(todoAdapter);
    }

    private void registerClick() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDoTitle = et_todo.getText().toString();
                String toDoDescrip = et_descrip.getText().toString();
                boolean important = sw.isChecked();

                if(Pattern.matches("s*", toDoTitle)) {
                    Toast.makeText(MainActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();
                } else if(Pattern.matches("s*", toDoDescrip)) {
                    Toast.makeText(MainActivity.this, "Description is missing", Toast.LENGTH_SHORT).show();
                } else {
                    DataModel dModel = new DataModel(toDoTitle, toDoDescrip, important);
                    dbHelper.addOne(dModel);

                    showAllToDos(dbHelper);

                    //empty input fields
                    et_todo.setText("");
                    et_descrip.setText("");
                    sw.setChecked(false);
                }
            }
        });
    }
}