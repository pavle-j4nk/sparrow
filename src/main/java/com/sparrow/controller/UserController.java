package com.sparrow.controller;

import com.sparrow.model.User;
import com.sparrow.response.UserProfileResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.ExceptionHandlerService;
import com.sparrow.service.impl.user.UserDoesNotExistException;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

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

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @ExceptionHandler(Exception.class)
    public void onException(Exception e, HttpServletResponse response) {
        this.exceptionHandlerService.handle(e, response);
    }

}