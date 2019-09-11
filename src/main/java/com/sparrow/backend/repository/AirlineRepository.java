package com.sparrow.backend.repository;

import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Airline getFirstByAdmin(User admin);

}
