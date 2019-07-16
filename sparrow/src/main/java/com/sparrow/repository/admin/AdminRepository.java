package com.sparrow.repository.admin;

import com.sparrow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {

}
