package com.sparrow.controller.user;

import com.sparrow.response.FriendRequestResponse;
import com.sparrow.response.UserResponse;
import com.sparrow.service.impl.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/user/friends")
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @GetMapping(value = {"", "/all"})
    public ResponseEntity<List<UserResponse>> getFriends(Principal principal) {
        return ResponseEntity.ok(UserResponse.of(friendshipService.getFriendsOf(principal.getName())));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity removeFriend(Principal principal, @PathVariable("email") String email) {
        friendshipService.breakFriendship(principal.getName(), email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/request/{email}")
    public ResponseEntity addFriend(Principal principal, @PathVariable("email") String email) {
        friendshipService.sendRequest(principal.getName(), email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/{email}/accept")
    public ResponseEntity acceptRequest(Principal principal, @PathVariable("email") String email) {
        friendshipService.acceptRequest(email, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/{email}/decline")
    public ResponseEntity declineRequest(Principal principal, @PathVariable("email") String email) {
        friendshipService.declineRequest(email, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = {"/request", "/request/all"})
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
