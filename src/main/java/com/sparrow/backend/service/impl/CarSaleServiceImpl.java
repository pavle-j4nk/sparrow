package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.CarSale;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.repository.CarSaleRepository;
import com.sparrow.backend.service.CarSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CarSaleServiceImpl implements CarSaleService {

    @Autowired
    CarSaleRepository carSaleRepository;

    @Autowired
    CarReservationRepository carReservationRepository;

    @Override
    public List<CarSale> findAll() {
        return this.carSaleRepository.findAll();
    }

    @Override
    public List<CarSale> findByDate(Date start , Date end) {

        List<CarSale> allOnSaleByDate = this.carSaleRepository.findByDate(start , end);
        List<CarReservation> allReservations = this.carReservationRepository.findByDate(start,end);

        for(CarReservation cr : allReservations) {
            Set<Car> cars = cr.getCars();
            for(Car car : cars) {
                allOnSaleByDate.removeIf(c -> c.getCar().getId().equals(car.getId()));
            }
        }

        return allOnSaleByDate;
    }

    @Override
    public CarSale createCarSale(CarSale carSale, Car car) {

        CarSale carSaleInput = new CarSale();
        carSaleInput.setCar(car);
        carSaleInput.setStart(carSale.getStart());
        carSaleInput.setEnd(carSale.getEnd());
        carSaleInput.setPrice(carSale.getPrice());

        return carSaleRepository.save(carSaleInput);
    }
}
