package com.example.studio_sqlite;

/**
 * Model of Data
 */

public class DataModel {

    //Attributes
    private int id;
    private String title;
    private String desription;
    private boolean isImportant;

    //Constructors
    public DataModel(String title, String desription, boolean isImportant) {
        this.title = title;
        this.desription = desription;
        this.isImportant = isImportant;
    }

    public DataModel(int id, String title, String desription, boolean isImportant) {
        this.id = id;
        this.title = title;
        this.desription = desription;
        this.isImportant = isImportant;
    }

    public DataModel() {

    }

    //toString
    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desription='" + desription + '\'' +
                ", isImportant=" + isImportant +
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

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
