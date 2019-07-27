package com.sparrow.controller;

import com.sparrow.model.User;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.impl.user.UserDoesNotExistException;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileResponse> getUser(Principal principal) {
        return ResponseEntity.ok(new UserProfileResponse(userService.findByUsername(principal.getName())));
    }

/*
    @PostMapping(path = "/me")
    public ResponseEntity updateProfile(Principal principal, @RequestBody @Valid User info) {
        userService.updateUserInfo(principal.getName(), info);
        return ResponseEntity.ok().build();
    }
*/

    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserResponse>> search(Principal principal
            , @PathVariable("name") String name
            , @RequestParam(name = "friend", required = false) Boolean isFriend
            , @RequestParam(name = "canAddFriend", required = false) Boolean canAddFriend) {

        return ResponseEntity
                .ok(UserResponse.of(userService.searchByAnyName(name, principal.getName(), isFriend, canAddFriend)));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(UserResponse.of(userService.findByUsername(id)));
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
    public ResponseEntity<Object> onException() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
