package com.example.lab4.domain;

public class CurrentUser {
    private static CurrentUser single_instance = null;
    public String firstName;
    public String lastName;

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        if (single_instance == null)
            single_instance = new CurrentUser();
        return single_instance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
