package com.example.studio_sqlite;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {

    public abstract ToDoDAO toDoDAO();

    //create instance of database
    Context context = GlobalHelperClass.getAppContext();
    ToDoDatabase db = Room.databaseBuilder(context, ToDoDatabase.class, "todo-database").build();

    ToDoDAO todoDao = db.toDoDAO();
    List<ToDo> todos = todoDao.getAll();



}
