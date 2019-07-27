package com.sparrow.repository;

import com.sparrow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    /**
     * Searches for users that contain name in firstName or lastName in lower case.
     * @param name
     * @return
     */
    @Query("SELECT u from User u WHERE u.enabled = true AND (lower(u.firstName) LIKE %?1% OR lower(u.lastName) LIKE %?1%)")
    List<User> findAllWhereAnyNameContains(String name);

}
