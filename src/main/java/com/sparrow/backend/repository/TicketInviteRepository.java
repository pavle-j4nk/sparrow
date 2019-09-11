package com.sparrow.backend.repository;

import com.sparrow.backend.model.TicketInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInviteRepository extends JpaRepository<TicketInvite, Long> {
}
