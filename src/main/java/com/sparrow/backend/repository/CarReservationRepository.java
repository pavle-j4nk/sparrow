package com.sparrow.backend.repository;

import com.sparrow.backend.model.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarReservationRepository extends JpaRepository<CarReservation , Long> {
}
