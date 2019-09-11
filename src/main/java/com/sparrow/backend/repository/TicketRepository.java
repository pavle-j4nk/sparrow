package com.sparrow.backend.repository;

import com.sparrow.backend.model.FlightTicket;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<FlightTicket, Long> {

    List<FlightTicket> getAllByUser(User user);

}
