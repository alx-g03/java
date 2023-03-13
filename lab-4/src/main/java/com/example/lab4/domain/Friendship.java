package com.example.lab4.domain;

import java.time.LocalDateTime;

public class Friendship extends Tuple<Long,Long> {
    private Status status;
    private LocalDateTime date;

    public Friendship() {
        super();
        this.status = Status.NONE;
        this.date = null;
    }

    public Friendship(Long idUser, Long idFriend) {
        super(idUser, idFriend);
        this.status = Status.NONE;
        this.date = null;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "idUser=" + this.getLeft() +
                ", idFriend=" + this.getRight() +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
