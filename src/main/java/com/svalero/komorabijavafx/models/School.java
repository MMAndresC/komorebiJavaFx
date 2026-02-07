package com.svalero.komorabijavafx.models;

public class School {
    private long id;
    private String name;
    private String city;
    private int students;
    private boolean publicSchool;
    private String registerDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public boolean isPublicSchool() {
        return publicSchool;
    }

    public void setPublicSchool(boolean publicSchool) {
        this.publicSchool = publicSchool;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
