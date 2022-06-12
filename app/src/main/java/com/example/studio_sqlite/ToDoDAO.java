package com.example.studio_sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object of Room Database.
 * Provides methods that the rest of the app uses to interact with data in the ToDo table.
 *
 * @author Nicole Gottschall
 * @since 2022-06-12
 */

@Dao
public interface ToDoDAO {

    @Query("SELECT * FROM todo")
    List<ToDo> getAll();

    @Query("SELECT * FROM todo WHERE tid IN (:todoIds)")
    List<ToDo> loadAllByIds(int[] todoIds);

    @Query("SELECT * FROM todo WHERE taskname LIKE :name")
    ToDo findByName(String name);

    @Insert
    void insertAll(ToDo... todos);

    @Delete
    void delete(ToDo todo);
}
