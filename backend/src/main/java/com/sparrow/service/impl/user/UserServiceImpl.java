package com.sparrow.service.impl.user;

import com.sparrow.model.user.User;
import com.sparrow.repository.UserRepository;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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


}
