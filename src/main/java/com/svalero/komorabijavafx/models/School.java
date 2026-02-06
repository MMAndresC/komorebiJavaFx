package com.svalero.komorabijavafx.models;

public class School {
    private long id;
    private String name;
    private String city;
    private int students;
    private boolean isPublic;
    private String registerDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
