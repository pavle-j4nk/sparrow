package com.sparrow.backend.repository;

import com.sparrow.backend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface CarRepository extends JpaRepository<Car , Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Car save(Car car);

}
