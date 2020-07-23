package com.snowman.sportclubolymp.model;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private int gender;
    private String sport;

    public Member(int id) {

    }

    public Member(String firstName, String lastName, int gender, String sport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.sport = sport;
    }

    public Member(int id, String firstName, String lastName, int gender, String sport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.sport = sport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGender() {
        return gender;
    }

    public String getSport() {
        return sport;
    }
}
