package com.example.studio_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Handles all Operations of Database
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODO_TABLE = "TODO_TABLE";
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
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
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

    public boolean addOne(DataModel dModel) {
        SQLiteDatabase db = this.getWritableDatabase(); //for insert actions
        ContentValues cv = new ContentValues(); //Content values stores data in pairs

        cv.put(COLUMN_TODO_TITLE, dModel.getTitle());
        cv.put(COLUMN_TODO_DESCRIPTION, dModel.getDesription());
        cv.put(COLUMN_IMPORTANT, dModel.isImportant());

        long insert = db.insert(TODO_TABLE, null, cv);
        //if insert is negative number than insert went wrong
        //if insert is positive number than isnert succeeded
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}
