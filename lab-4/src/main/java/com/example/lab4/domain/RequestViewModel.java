package com.example.lab4.domain;

import java.time.LocalDateTime;

public class RequestViewModel {
    private String friendFirstName;
    private String friendLastName;
    private Status status;
    private LocalDateTime date;

    public RequestViewModel(String friendFirstName, String friendLastName, Status status, LocalDateTime date) {
        this.friendFirstName = friendFirstName;
        this.friendLastName = friendLastName;
        this.status = status;
        this.date = date;
    }

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
