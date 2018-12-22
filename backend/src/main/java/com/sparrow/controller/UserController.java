package com.sparrow.controller;

import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.UserService;
import com.sparrow.service.impl.user.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUser(Principal principal) {
        return ResponseEntity.ok(new UserProfileResponse(userService.findByEmail(principal.getName())));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(new UserResponse(userService.findByEmail(id)));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        return null;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable("id") String id) {
        return null;
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<Object> onUserNotFundExeption() {
        return ResponseEntity.notFound().build();
    }
}
