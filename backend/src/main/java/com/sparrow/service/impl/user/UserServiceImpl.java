package com.sparrow.service.impl.user;

import com.sparrow.model.user.FriendRequest;
import com.sparrow.model.user.User;
import com.sparrow.repository.user.UserRepository;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipService friendshipService;

    @Override

    public User findById(Long id) {
        AtomicReference<User> user = null;
        return userRepository.findById(id).orElseGet(() -> {
            throw new UserDoesNotExistException(id.toString());
        });
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            throw new UserDoesNotExistException(email);
        });
    }

    @Override
    public UserProfileResponse getProfile(String email) {
        User user = findByEmail(email);
        return new UserProfileResponse(user);
    }

    @Override
    public UserResponse getUser(String email) {
        User user = findByEmail(email);
        return new UserResponse(user);
    }

    @Override
    public void updateUserInfo(String email, User userInfo) {
        User user = findByEmail(email);

        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setAddress(userInfo.getAddress());

        userRepository.save(user);
    }

    @Override
    public List<User> searchByAnyName(String name) {
        return userRepository.findAllWhereAnyNameContains(name.toLowerCase());
    }

    @Override
    public List<User> searchByAnyName(String name, String invoker, Boolean isFriend, Boolean canAddFriend) {
        List<User> users = searchByAnyName(name);

        if (users.isEmpty() || (isFriend == null && canAddFriend == null)) {
            return users;
        }

        List<User> friends = friendshipService.getFriendsOf(invoker);

        if (isFriend != null) {
            if (isFriend) {
                users.removeIf(u -> !friends.contains(u));
            } else {
                users.removeIf(u -> friends.contains(u));
            }
        }

        if (canAddFriend != null) {
            List<FriendRequest> requests = friendshipService.getRequestOf(invoker);
            List<User> requestsTo = new ArrayList<>();
            requests.stream().forEach(r -> requestsTo.add(r.getReceiver()));

            if (canAddFriend) {
                users.removeIf(u -> friends.contains(u) || requestsTo.contains(u));
            } else {
                users.removeIf(u -> !(friends.contains(u) || requestsTo.contains(u)));
            }
        }

        return users;
    }


}
