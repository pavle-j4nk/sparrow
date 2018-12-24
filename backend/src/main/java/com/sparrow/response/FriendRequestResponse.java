package com.sparrow.response;

import com.sparrow.model.user.FriendRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendRequestResponse {
    private Long id;
    private String sender;
    private String reciever;
    private String status;

    public FriendRequestResponse() {
    }

    public FriendRequestResponse(Long id, String sender, String reciever, String status) {
        this.id = id;
        this.sender = sender;
        this.reciever = reciever;
        this.status = status;
    }

    public FriendRequestResponse(FriendRequest request) {
        this(request.getId(), request.getSender().getEmail(), request.getReceiver().getEmail()
                , request.getStatus().name());
    }

    public static FriendRequestResponse of(FriendRequest request) {
        return new FriendRequestResponse(request);
    }

    public static List<FriendRequestResponse> of(List<FriendRequest> requests) {
        List<FriendRequestResponse> responses = new ArrayList<>();
        for (FriendRequest request : requests) {
            responses.add(of(request));
        }
        return responses;
    }

    public static List<FriendRequestResponse> of(FriendRequest... requests) {
        return of(Arrays.asList(requests));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
