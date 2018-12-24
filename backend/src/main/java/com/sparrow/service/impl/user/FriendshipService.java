package com.sparrow.service.impl.user;

import com.sparrow.model.user.FriendRequest;
import com.sparrow.model.user.User;

import java.util.List;

public interface FriendshipService {

    List<User> getFriendsOf(String email);

    void createFriendship(String email1, String email2);

    void breakFriendship(String email1, String email2);

    FriendRequest findRequest(String senderEmail, String receiverEmail);

    FriendRequest findRequest(User sender, User receiver);

    void sendRequest(String emailFrom, String emailTo);

    void acceptRequest(String emailFrom, String emailTo);

    void declineRequest(String emailFrom, String emailTo);

    List<FriendRequest> getRequestFor(String receiver);

}
