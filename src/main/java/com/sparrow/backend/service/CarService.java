package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface CarService {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Car createCar(Car car , Long rentaCarId);

    Car findById(Long id);

    void delete(Car car);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Car updateCar(Car newCar, Long oldCar);
}
