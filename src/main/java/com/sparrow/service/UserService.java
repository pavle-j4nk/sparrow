package com.sparrow.service;

import com.sparrow.dto.ExtendedUser;
import com.sparrow.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    ExtendedUser findExtendedByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();

    Page<User> findAll(int page, int pageSize);

    Page<User> findAll(int page, int pageSize, String orderBy);

    List<User> searchByAnyName(String name, String name1, Boolean isFriend, Boolean canAddFriend);

    User create(User user);
}
