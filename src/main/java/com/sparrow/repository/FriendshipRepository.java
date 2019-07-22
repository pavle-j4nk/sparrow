package com.sparrow.repository;

import com.sparrow.model.Friendship;
import com.sparrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findAllByFriendsContaining(User user);

    List<Friendship> findAllByFriendsContainingAndFriendsContaining(User user1, User user2);


}
