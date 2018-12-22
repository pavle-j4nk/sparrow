package com.sparrow.service;

import com.sparrow.model.user.User;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    UserProfileResponse getProfile(String email);

    UserResponse getUser(String email);
}
