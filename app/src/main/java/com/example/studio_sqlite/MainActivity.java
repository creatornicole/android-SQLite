package com.example.studio_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //References to buttons and other controls on the layout
    Button btn_add;
    EditText et_todo, et_descrip;
    Switch sw;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to variables
        btn_add = findViewById(R.id.btn_add);
        et_todo = findViewById(R.id.et_todo);
        et_descrip = findViewById(R.id.et_descrip);
        sw = findViewById(R.id.sw);
        lv = findViewById(R.id.lv);

        //button click listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDo = et_todo.getText().toString();
                if(Pattern.matches("s*", toDo)) {
                    Toast.makeText(MainActivity.this, "Title needed", Toast.LENGTH_SHORT).show();
                } else {
                    DataModel dm = new DataModel(-1, toDo, et_descrip.getText().toString(), sw.isChecked());
                    Toast.makeText(MainActivity.this, dm.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}