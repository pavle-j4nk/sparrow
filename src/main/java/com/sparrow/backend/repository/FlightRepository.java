package com.sparrow.backend.repository;

import com.sparrow.backend.model.Destination;
import com.sparrow.backend.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByFromAndToAndDepartureDate(Destination from, Destination to, LocalDate departure);

}
