package com.sparrow.service.impl;

import com.sparrow.model.User;
import com.sparrow.repository.RoleRepository;
import com.sparrow.repository.UserRepository;
import com.sparrow.service.exception.UserDoesNotExist;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElseGet(() -> {
            throw new UserDoesNotExist(id.toString());
        });
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseGet(() -> {
            throw new UserDoesNotExist(username);
        });
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
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
    public List<User> searchByAnyName(String name, String name1, Boolean isFriend, Boolean canAddFriend) {
        return userRepository.findAllWhereAnyNameContains(name);
    }

}
