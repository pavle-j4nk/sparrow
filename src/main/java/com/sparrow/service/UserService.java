package com.sparrow.service;

import com.sparrow.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    Page<User> findAll(int page, int pageSize);

    Page<User> findAll(int page, int pageSize, String orderBy);

    List<User> searchByAnyName(String name, String name1, Boolean isFriend, Boolean canAddFriend);
}
