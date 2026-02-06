package com.svalero.komorabijavafx.models;

public class Project {
    private long id;
    private String name;
    private String description;
    private int ods;
    private boolean active;
    private String startDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
