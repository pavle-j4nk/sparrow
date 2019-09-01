package com.sparrow.backend.service.impl.user;

import com.sparrow.backend.model.FriendRequest;
import com.sparrow.backend.model.Friendship;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.FriendRequestRepository;
import com.sparrow.backend.repository.FriendshipRepository;
import com.sparrow.backend.service.UserService;
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
    public List<User> getFriendsOf(String username) {
        User user = userService.findByUsername(username);

        List<Friendship> friendships = friendshipRepository.findAllByFriendsContaining(user);

        List<User> friends = new ArrayList<>();
        for (Friendship friendship : friendships) {
            friends.addAll(friendship.getFriends());
        }

        friends.remove(user);

        return friends;
    }

    @Override
    public void createFriendship(String username1, String username2) {
        User user1 = userService.findByUsername(username1);
        User user2 = userService.findByUsername(username2);

        Friendship friendship = new Friendship();
        friendship.setFriends(Arrays.asList(user1, user2));

        friendshipRepository.save(friendship);
    }

    @Override
    public void breakFriendship(String username1, String username2) {
        User user1 = userService.findByUsername(username1);
        User user2 = userService.findByUsername(username2);

        List<Friendship> friendships = friendshipRepository
                .findAllByFriendsContainingAndFriendsContaining(user1, user2);
        if (friendships.isEmpty()) {
            throw new FriendshipDoesNotExistException(String.format("Between %s and %s", username1, username2));
        }

        friendshipRepository.delete(friendships.get(0));
    }

    @Override
    public FriendRequest findRequest(String senderUsername, String receiverUsername) {
        User sender = userService.findByUsername(senderUsername);
        User receiver = userService.findByUsername(receiverUsername);

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
    public void sendRequest(String usernameFrom, String usernameTo) {
        User sender = userService.findByUsername(usernameFrom);
        User receiver = userService.findByUsername(usernameTo);

        if (!friendRequestRepository.findBySenderAndReceiverAndStatus(sender, receiver, FriendRequest.Status.PENDING)
                .isEmpty()) {
            throw new FriendRequestAlreadyExistsException(String.format("From %s to %s", usernameFrom, usernameTo));
        }

        if (!friendRequestRepository.findBySenderAndReceiverAndStatus(receiver, sender, FriendRequest.Status.PENDING)
                .isEmpty()) {
            throw new FriendRequestAlreadyExistsException(String.format("From %s to %s", usernameTo, usernameFrom));
        }


        FriendRequest request = new FriendRequest(sender, receiver, FriendRequest.Status.PENDING);

        friendRequestRepository.save(request);
    }

    @Override
    public void acceptRequest(String usernameFrom, String usernameTo) {
        FriendRequest request = findRequest(usernameFrom, usernameTo);
        request.setStatus(FriendRequest.Status.ACCEPTED);
        friendRequestRepository.save(request);

        createFriendship(usernameTo, usernameFrom);
    }

    @Override
    public void declineRequest(String usernameFrom, String usernameTo) {
        FriendRequest request = findRequest(usernameFrom, usernameTo);
        request.setStatus(FriendRequest.Status.DECLINED);
        friendRequestRepository.save(request);
    }

    @Override
    public List<FriendRequest> getRequestFor(String receiver) {
        return friendRequestRepository
                .findAllByReceiverAndStatus(userService.findByUsername(receiver), FriendRequest.Status.PENDING);
    }

    @Override
    public List<FriendRequest> getRequestOf(String sender) {
        return friendRequestRepository
                .findAllBySenderAndStatus(userService.findByUsername(sender), FriendRequest.Status.PENDING);
    }


}
