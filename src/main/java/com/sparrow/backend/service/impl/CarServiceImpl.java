package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.repository.CarRepository;
import com.sparrow.backend.repository.RentACarRepository;
import com.sparrow.backend.service.CarService;
import com.sparrow.backend.service.RentACarService;
import com.sparrow.backend.service.exception.RentacarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    RentACarRepository rentACarRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    RentACarService rentACarService;

    @Override
    public Car createCar(Car car, Long rentaCarId) {

        RentACar rentACar = rentACarService.findById(rentaCarId);

        Car inputCar = new Car();

        inputCar.setRentACar(rentACar);
        inputCar.setSeats(car.getSeats());
        inputCar.setCarManufacturer(car.getCarManufacturer());
        inputCar.setCarModel(car.getCarModel());
        inputCar.setCarType(car.getCarType());
        inputCar.setPrice(car.getPrice());
        inputCar.setYearOfManufacturing(car.getYearOfManufacturing());

        carRepository.save(inputCar);

        return inputCar;
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()) {
            return car.get();
        }else{
            throw new RentacarNotFoundException(id); /*TODO MAKE CAR NOT FOUND*/
        }    }

    @Override
    public void delete(Car car) {

        carRepository.delete(car);
    }

    @Override
    public Car updateCar(Car newCar, Long oldCar) {
        Car carForReturn = carRepository.getOne(oldCar);

        carForReturn.setCarManufacturer(newCar.getCarManufacturer());
        carForReturn.setSeats(newCar.getSeats());
        carForReturn.setPrice(newCar.getPrice());
        carForReturn.setCarType(newCar.getCarType());
        carForReturn.setCarModel(newCar.getCarModel());
        carForReturn.setYearOfManufacturing(newCar.getYearOfManufacturing());

        return  carRepository.save(carForReturn);
    }


}
