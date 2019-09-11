package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;

public interface CarService {

    Car createCar(Car car , Long rentaCarId);

    Car findById(Long id);

    void delete(Car car);

    Car updateCar(Car newCar, Long oldCar);
}
