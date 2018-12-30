package com.sparrow.service.impl.user;

import com.sparrow.model.user.FriendRequest;
import com.sparrow.model.user.Friendship;
import com.sparrow.model.user.User;
import com.sparrow.repository.user.FriendshipRepository;
import com.sparrow.repository.user.FriendRequestRepository;
import com.sparrow.service.impl.user.exception.FriendRequestAlreadyExistsException;
import com.sparrow.service.impl.user.exception.FriendRequestDoesNotExistException;
import com.sparrow.service.impl.user.exception.FriendshipAlreadyExistsException;
import com.sparrow.service.impl.user.exception.FriendshipDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    UserService userService;

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @Override
    public List<User> getFriendsOf(String email) {
        User user = userService.findByEmail(email);

        List<Friendship> friendships = friendshipRepository.findAllByFriendsContaining(user);

        List<User> friends = new ArrayList<>();
        for (Friendship friendship : friendships) {
            friends.addAll(friendship.getFriends());
        }

        friends.remove(user);

        return friends;
    }

    @Override
    public void createFriendship(String email1, String email2) {
        User user1 = userService.findByEmail(email1);
        User user2 = userService.findByEmail(email2);

        if (email1.equals(email2) || getFriendsOf(email1).contains(user2)) {
            throw new FriendshipAlreadyExistsException(String.format("Between %s and %s", email1, email2));
        }

        Friendship friendship = new Friendship();
        friendship.setFriends(Arrays.asList(user1, user2));

        friendshipRepository.save(friendship);
    }

    @Override
    public void breakFriendship(String email1, String email2) {
        User user1 = userService.findByEmail(email1);
        User user2 = userService.findByEmail(email2);

        List<Friendship> friendships = friendshipRepository
                .findAllByFriendsContainingAndFriendsContaining(user1, user2);
        if (friendships.isEmpty()) {
            throw new FriendshipDoesNotExistException(String.format("Between %s and %s", email1, email2));
        }

        friendshipRepository.delete(friendships.get(0));
    }

    @Override
    public FriendRequest findRequest(String senderEmail, String receiverEmail) {
        User sender = userService.findByEmail(senderEmail);
        User receiver = userService.findByEmail(receiverEmail);

        return findRequest(sender, receiver);
    }

    @Override
    public FriendRequest findRequest(User sender, User receiver) {
        List<FriendRequest> friendRequests = friendRequestRepository
                .findBySenderAndReceiverAndStatus(sender, receiver, FriendRequest.Status.PENDING);

        if (friendRequests.isEmpty()) {
            throw new FriendRequestDoesNotExistException(
                    String.format("From %s to %s", sender.getEmail(), receiver.getEmail()));
        }

        return friendRequests.get(0);
    }

    @Override
    public void sendRequest(String emailFrom, String emailTo) {
        User sender = userService.findByEmail(emailFrom);
        User receiver = userService.findByEmail(emailTo);

        if (emailFrom.equals(emailTo) || getFriendsOf(emailTo).contains(sender)) {
            throw new FriendshipAlreadyExistsException(String.format("Between %s and %s", emailFrom, emailTo));
        }

        if (!friendRequestRepository.findBySenderAndReceiverAndStatus(sender, receiver, FriendRequest.Status.PENDING)
                .isEmpty()) {
            throw new FriendRequestAlreadyExistsException(String.format("From %s to %s", emailFrom, emailTo));
        }

        if (!friendRequestRepository.findBySenderAndReceiverAndStatus(receiver, sender, FriendRequest.Status.PENDING)
                .isEmpty()) {
            throw new FriendRequestAlreadyExistsException(String.format("From %s to %s", emailTo, emailFrom));
        }

        FriendRequest request = new FriendRequest(sender, receiver, FriendRequest.Status.PENDING);

        friendRequestRepository.save(request);
    }

    @Override
    public void acceptRequest(String emailFrom, String emailTo) {
        FriendRequest request = findRequest(emailFrom, emailTo);
        request.setStatus(FriendRequest.Status.ACCEPTED);
        friendRequestRepository.save(request);

        createFriendship(emailTo, emailFrom);
    }

    @Override
    public void declineRequest(String emailFrom, String emailTo) {
        FriendRequest request = findRequest(emailFrom, emailTo);
        request.setStatus(FriendRequest.Status.DECLINED);
        friendRequestRepository.save(request);
    }

    @Override
    public List<FriendRequest> getRequestFor(String receiver) {
        return friendRequestRepository
                .findAllByReceiverAndStatus(userService.findByEmail(receiver), FriendRequest.Status.PENDING);
    }

    @Override
    public List<FriendRequest> getRequestOf(String sender) {
        return friendRequestRepository
                .findAllBySenderAndStatus(userService.findByEmail(sender), FriendRequest.Status.PENDING);
    }


}
