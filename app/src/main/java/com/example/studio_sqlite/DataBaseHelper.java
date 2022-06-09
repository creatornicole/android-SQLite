package com.example.studio_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Handles all Operations of Database
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TODO_TITLE = "TODO_TITLE";
    public static final String COLUMN_TODO_DESCRIPTION = "TODO_DESCRIPTION";
    public static final String COLUMN_IMPORTANT = "IMPORTANT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    /**
     * Is called when the app requests or inputs new data.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + TODO_TABLE
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TODO_TITLE + " TEXT, "
                + COLUMN_TODO_DESCRIPTION + " TEXT, "
                + COLUMN_IMPORTANT + " BOOL)";

        db.execSQL(createTableStatement);
        //create new Table
    }

    /**
     * Called whenever the database version number changes.
     * Prevents the previous apps from crashing.
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * method to add new database entry
     *
     * @param dModel
     * @return
     */
    public boolean addOne(DataModel dModel) {
        SQLiteDatabase db = this.getWritableDatabase(); //for insert actions
        ContentValues cv = new ContentValues(); //Content values stores data in pairs

        cv.put(COLUMN_TODO_TITLE, dModel.getTitle());
        cv.put(COLUMN_TODO_DESCRIPTION, dModel.getDesription());
        cv.put(COLUMN_IMPORTANT, dModel.isImportant());

        long insert = db.insert(TODO_TABLE, null, cv);

        //clean up, close connection to database and cursor
        db.close();
        if(insert == -1) { //if insert is negative number than insert went wrong
            return false;
        } else { //if insert is positive number than insert succeeded
            return true;
        }
    }

    public boolean deleteOne(DataModel dModel) {
        //if DataModel is found in the database, delete it and return true
        //if it is not found, return false
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + TODO_TABLE
                + " WHERE " + COLUMN_TODO_TITLE + " = " + dModel.getTitle()
                + " AND " + COLUMN_TODO_DESCRIPTION + " = " + dModel.getDesription();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<DataModel> getAllAsList() {
        //create empty list
        ArrayList<DataModel> returnList = new ArrayList<>();
        //get data from the database
        String queryString = "SELECT * FROM " + TODO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase(); //get data from database
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) { //returns a true if there were items selected
            //loop through results, create new todo objects, put them into return list
            do {
                String todoTitle = cursor.getString(1);
                String todoDescrip = cursor.getString(2);
                boolean important = cursor.getInt(3) == 1 ? true: false;

                DataModel newTodo = new DataModel(todoTitle, todoDescrip, important);
                returnList.add(newTodo);

            } while(cursor.moveToNext());
        } else { //returns a false if no items were selected
            //failure, to not add anything to the list
        }

        //clean up, close connection to database and cursor
        cursor.close();
        db.close();

        return returnList;
    }

    private TreeMap<String, ArrayList<String>> getIdPairs() {
        ArrayList<String> value = new ArrayList<String>();
        TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();

        //get data from database
        String queryString = "SELECT * FROM " + TODO_TABLE;
        SQLiteDatabase db = this.getWritableDatabase(); //get data from database
        Cursor cursor = db.rawQuery(queryString, null);

        String id, title, descrip, importance;
        if(cursor.moveToFirst()) {
            do {
                id = cursor.getString(0);
                title = cursor.getString(1);
                descrip = cursor.getString(2);
                importance = Integer.toString(cursor.getInt(3));

                value.add(title);
                value.add(descrip);
                value.add(importance);

                map.put(id, value);

                //clear array again
                for(String todoInfos: value) {
                    todoInfos = null;
                }
            } while(cursor.moveToNext());
        }

        //clean up, close connection to database and cursor
        cursor.close();
        db.close();
        return map;
    }
}
