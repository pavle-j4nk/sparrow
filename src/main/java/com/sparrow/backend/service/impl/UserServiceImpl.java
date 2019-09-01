package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.ExtendedUser;
import com.sparrow.backend.model.FriendRequest;
import com.sparrow.backend.model.Role;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.RoleRepository;
import com.sparrow.backend.repository.UserRepository;
import com.sparrow.backend.service.UserService;
import com.sparrow.backend.service.exception.NotFoundException;
import com.sparrow.backend.service.exception.UserAlreadyExists;
import com.sparrow.backend.service.exception.UserDoesNotExist;
import com.sparrow.backend.service.impl.user.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private FriendshipService friendshipService;

    @Override
    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(() -> {
            throw new UserDoesNotExist(id.toString());
        });
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseGet(() -> {
            throw new UserDoesNotExist(username);
        });
    }

    @Override
    public ExtendedUser findExtendedByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            throw new UserDoesNotExist(username);
        });

        List<FriendRequest> friendshipRequests = friendshipService.getRequestFor(username);

        return new ExtendedUser(user, friendshipRequests.size());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            throw new UserDoesNotExist(email);
        });
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByRole(String roleName) {
        Role role = roleRepo.findByName(roleName).orElseGet(() -> {
            throw new NotFoundException("Role does not exist: " + roleName);
        });

        return userRepository.findAllByRole(role);
    }

    @Override
    public Page<User> findAll(int page, int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Page<User> findAll(int page, int pageSize, String orderBy) {
        return userRepository.findAll(PageRequest.of(page, pageSize, Sort.by(orderBy)));
    }

    @Override
    public List<User> searchByAnyName(String query, String loggedUsername, Boolean isFriend, Boolean canAddFriend) {
        List<User> friendsOf = friendshipService.getFriendsOf(loggedUsername);
        List<User> results = userRepository.findAllWhereAnyNameContains(query, roleRepo.findByName(Role.USER).get());

        results.remove(findByUsername(loggedUsername));

        if (isFriend) {
            results.removeIf(user -> !friendsOf.contains(user));
        } else {
            results.removeIf(friendsOf::contains);
        }

        List<FriendRequest> requests = friendshipService.getRequestFor(loggedUsername);
        requests.addAll(friendshipService.getRequestOf(loggedUsername));

        Set<User> requestUsers = new HashSet<>();
        for (FriendRequest request : requests) {
            requestUsers.add(request.getSender());
            requestUsers.add(request.getReceiver());
        }

        if (canAddFriend) {
            results.removeIf(requestUsers::contains);
        }

        return results;
    }

    @Override
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExists(user.getEmail());
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExists(user.getUsername());
        }

        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepo.findByName(Role.USER).get());

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User old = findByUsername(user.getUsername());
        old.setFirstName(user.getFirstName());
        old.setLastName(user.getLastName());
        old.setAddress(user.getAddress());

        if (user.getPassword() != null) {
            old.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(old);
    }

}
