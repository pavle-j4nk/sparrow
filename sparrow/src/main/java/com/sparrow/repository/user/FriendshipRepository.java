package com.sparrow.repository.user;

import com.sparrow.model.user.Friendship;
import com.sparrow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findAllByFriendsContaining(User user);

    List<Friendship> findAllByFriendsContainingAndFriendsContaining(User user1, User user2);


}
