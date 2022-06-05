package com.example.studio_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //References to buttons and other controls on the layout
    Button btn_add, btn_view;
    EditText et_todo, et_descrip;
    Switch sw;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to variables
        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        et_todo = findViewById(R.id.et_todo);
        et_descrip = findViewById(R.id.et_descrip);
        sw = findViewById(R.id.sw);
        lv = findViewById(R.id.lv);

        //button click listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDo = et_todo.getText().toString();
                DataModel dm;
                if(Pattern.matches("s*", toDo)) {
                    dm = new DataModel(-1, "ERROR", "", false);
                } else {
                    dm = new DataModel(-1, toDo, et_descrip.getText().toString(), sw.isChecked());
                }

                DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dbHelper.addOne(dm);

                Toast.makeText(MainActivity.this, "Success: " + success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this);
                List<DataModel> allTodos = dbHelper.getAll();

                ArrayAdapter todoArrayAdapter = new ArrayAdapter<DataModel>(MainActivity.this, android.R.layout.simple_list_item_1, allTodos);
                lv.setAdapter(todoArrayAdapter);
            }
        });
    }
}