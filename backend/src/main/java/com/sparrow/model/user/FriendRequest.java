package com.sparrow.model.user;

import javax.persistence.*;

@Entity
public class FriendRequest {
    public enum Status {PENDING, ACCEPTED, DECLINED};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User reciever;

    private Status status;

    public FriendRequest() {
    }

    public FriendRequest(User sender, User reciever, Status status) {
        this.sender = sender;
        this.reciever = reciever;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
