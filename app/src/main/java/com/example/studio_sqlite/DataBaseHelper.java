package com.example.studio_sqlite;

import android.annotation.SuppressLint;
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
    private Context mContext;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODO_TABLE
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TODO_TITLE + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(DataModel dModel) {
        SQLiteDatabase db = this.getWritableDatabase(); //for insert actions
        ContentValues cv = new ContentValues(); //Content values stores data in pairs
        cv.put(COLUMN_TODO_TITLE, dModel.getTitle());
        return db.insert(TODO_TABLE, null, cv) > -1;
    }

    public boolean deleteOne(DataModel dModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TODO_TABLE,COLUMN_TODO_TITLE +"=?",new String[]{dModel.getTitle()}) > 0;
    }

    @SuppressLint("Range")
    public ArrayList<DataModel> getAllAsList() {
        //create empty list
        ArrayList<DataModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TODO_TABLE,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            returnList.add(new DataModel(cursor.getString(cursor.getColumnIndex(COLUMN_TODO_TITLE))));
        }
        cursor.close();
        return returnList;
    }

    public boolean existsInDB(DataModel dModel) {
        boolean rv = false;
        SQLiteDatabase db = this.getWritableDatabase(); //for insert actions
        Cursor cursor = db.query(TODO_TABLE,null,COLUMN_TODO_TITLE+"=?",new String[]{dModel.getTitle()},null,null,null);
        if(cursor.moveToFirst()) {
            rv = true;
        }
        cursor.close();
        return rv;
    }
}
