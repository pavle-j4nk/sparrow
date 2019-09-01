package com.sparrow.backend.repository;

import com.sparrow.backend.model.Role;
import com.sparrow.backend.model.User;
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
    @Query("SELECT u from User u WHERE u.enabled = true AND (lower(u.firstName) LIKE %?1% OR lower(u.lastName) LIKE %?1%)" +
            " AND u.role = ?2")
    List<User> findAllWhereAnyNameContains(String name, Role role);

    List<User> findAllByRole(Role role);
}
