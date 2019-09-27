package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;

import java.sql.Date;
import java.util.List;

public interface CarSaleService {
    List<CarSale> findAll();

    List<CarSale> findByDate(Date start , Date end);

    CarSale createCarSale(CarSale carSale , Car car);
}
