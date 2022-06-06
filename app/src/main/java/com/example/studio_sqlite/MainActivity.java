package com.example.studio_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private Button btn_add, btn_view;
    private EditText et_todo, et_descrip;
    private Switch sw;
    private ListView lv;
    private DataAdapter todoAdapter;
    private static List<DataModel> allTodos;
    private DataBaseHelper dbHelper;

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

        dbHelper = new DataBaseHelper(MainActivity.this);

        showAllToDos(dbHelper);

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

                showAllToDos(dbHelper);
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new DataBaseHelper(MainActivity.this);
                showAllToDos(dbHelper);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { //will give you information about which item was clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(position);
                dbHelper.deleteOne(clickedItem);

                showAllToDos(dbHelper);

                Toast.makeText(MainActivity.this, "Deleted " + clickedItem.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAllToDos(DataBaseHelper dbHelper) {
        todoAdapter = new DataAdapter<DataModel>(MainActivity.this, dbHelper.getAll());
        lv.setAdapter(todoAdapter);
    }

    public static List<DataModel> getList() {
        return allTodos;
    }
}