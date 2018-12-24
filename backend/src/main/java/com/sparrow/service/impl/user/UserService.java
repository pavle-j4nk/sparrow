package com.sparrow.service.impl.user;

import com.sparrow.model.user.User;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    UserProfileResponse getProfile(String email);

    UserResponse getUser(String email);

    /**
     * Update user info.
     *
     * @param email   user identifier
     * @param newInfo information holder
     */
    void updateUserInfo(String email, User newInfo);

    List<User> searchByAnyName(String name);

}
