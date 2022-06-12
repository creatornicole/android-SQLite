package com.example.studio_sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data entity of Room Database.
 * Represents tables in database.
 *
 * @author Nicole Gottschall
 * @since 2022-06-12
 */

@Entity
public class ToDo {

    @PrimaryKey
    public int tid;

    @ColumnInfo
    public String taskname;

}
