package com.sparrow.backend.repository;

import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Override
    <S extends Airline> S save(S s);

    Airline getFirstByAdmin(User admin);

}
