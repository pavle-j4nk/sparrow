package com.sparrow.repository.user;

import com.sparrow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u from User u WHERE u.enabled = true AND (u.firstName LIKE %?1% OR u.lastName LIKE %?1%)")
    List<User> findAllWhereAnyNameContains(String name);

}
