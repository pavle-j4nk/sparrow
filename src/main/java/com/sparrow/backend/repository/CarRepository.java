package com.sparrow.backend.repository;

import com.sparrow.backend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car , Long> {
}
