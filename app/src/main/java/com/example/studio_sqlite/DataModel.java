package com.example.studio_sqlite;

/**
 * Model of Data
 */

public class DataModel {

    //Attributes
    private int id;
    private String title;

    //Constructors
    public DataModel(String title) {
        this.title = title;
    }

    public DataModel() {

    }

    //toString
    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
