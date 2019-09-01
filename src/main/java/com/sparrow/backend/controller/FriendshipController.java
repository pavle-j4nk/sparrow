package com.sparrow.backend.controller;

import com.sparrow.backend.response.FriendRequestResponse;
import com.sparrow.backend.response.UserResponse;
import com.sparrow.backend.service.impl.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/user/friends")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @GetMapping(value = {"", "/all"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserResponse>> getFriends(Principal principal) {
        return ResponseEntity.ok(UserResponse.of(friendshipService.getFriendsOf(principal.getName())));
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity removeFriend(Principal principal, @PathVariable("username") String email) {
        friendshipService.breakFriendship(principal.getName(), email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/request/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity addFriend(Principal principal, @PathVariable("username") String email) {
        friendshipService.sendRequest(principal.getName(), email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/{email}/accept")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity acceptRequest(Principal principal, @PathVariable("email") String email) {
        friendshipService.acceptRequest(email, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/{username}/decline")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity declineRequest(Principal principal, @PathVariable("username") String email) {
        friendshipService.declineRequest(email, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = {"/request", "/request/all"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FriendRequestResponse>> getRequests(Principal principal) {
        return ResponseEntity.ok(FriendRequestResponse.of(friendshipService.getRequestFor(principal.getName())));
    }

    @ExceptionHandler({UserDoesNotExistException.class, FriendshipDoesNotExistException.class
            , FriendRequestDoesNotExistException.class, FriendRequestAlreadyExistsException.class})
    public ResponseEntity<String> onException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format( "%s: %s", e.getClass().getSimpleName(), e.getMessage()));
    }

}
