package com.sparrow.service.impl.user;

import com.sparrow.model.FriendRequest;
import com.sparrow.model.User;

import java.util.List;

public interface FriendshipService {

    List<User> getFriendsOf(String username);

    void createFriendship(String username1, String username2);

    void breakFriendship(String username1, String username2);

    FriendRequest findRequest(String senderUsername, String receiverUsername);

    FriendRequest findRequest(User sender, User receiver);

    void sendRequest(String usernameFrom, String usernameTo);

    void acceptRequest(String usernameFrom, String usernameTo);

    void declineRequest(String usernameFrom, String usernameTo);

    List<FriendRequest> getRequestFor(String receiver);

    List<FriendRequest> getRequestOf(String sender);

}
