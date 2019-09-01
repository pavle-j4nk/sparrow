package com.sparrow.backend.response;

import com.sparrow.backend.model.FriendRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendRequestResponse {
    private Long id;
    private UserResponse sender;
    private String receiver;
    private String status;

    public FriendRequestResponse() {
    }

    public FriendRequestResponse(Long id, UserResponse sender, String receiver, String status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public FriendRequestResponse(FriendRequest request) {
        this(request.getId(), UserResponse.of(request.getSender()), request.getReceiver().getEmail()
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

    public UserResponse getSender() {
        return sender;
    }

    public void setSender(UserResponse sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
