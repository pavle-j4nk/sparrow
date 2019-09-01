package com.sparrow.backend.repository;

import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {

}
