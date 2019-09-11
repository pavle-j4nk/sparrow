package com.sparrow.backend.repository;

import com.sparrow.backend.model.FlightTicket;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface TicketRepository extends JpaRepository<FlightTicket, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Override
    <S extends FlightTicket> S save(S s);

    List<FlightTicket> getAllByUser(User user);

}
