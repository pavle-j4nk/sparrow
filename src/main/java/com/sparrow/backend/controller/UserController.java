package com.sparrow.backend.controller;

import com.sparrow.backend.dto.ExtendedUser;
import com.sparrow.backend.model.User;
import com.sparrow.backend.response.UserProfileResponse;
import com.sparrow.backend.response.UserResponse;
import com.sparrow.backend.service.ExceptionHandlerService;
import com.sparrow.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ExtendedUser> getUser(Principal principal) {
        return ResponseEntity.ok(userService.findExtendedByUsername(principal.getName()));
    }

    @GetMapping("/search/{name}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserResponse>> search(Principal principal
            , @PathVariable("name") String name
            , @RequestParam(name = "isFriend", required = false) Boolean isFriend
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

    @GetMapping({"", "/all"})
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "role", required = false) String role) {
        if(role == null)
            return ResponseEntity.ok(userService.findAll());
        else
            return ResponseEntity.ok(userService.findAllByRole(role));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Valid User user, Principal principal) {
        user.setUsername(principal.getName());
        return ResponseEntity.ok(userService.update(user));
    }

    @ExceptionHandler(Exception.class)
    public void onException(Exception e, HttpServletResponse response) {
        this.exceptionHandlerService.handle(e, response);
    }

}
