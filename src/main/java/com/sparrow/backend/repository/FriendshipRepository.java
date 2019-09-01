package com.sparrow.backend.repository;

import com.sparrow.backend.model.Friendship;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findAllByFriendsContaining(User user);

    List<Friendship> findAllByFriendsContainingAndFriendsContaining(User user1, User user2);


}
