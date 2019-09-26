package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;

import java.util.List;

public interface CarSaleService {
    List<CarSale> findAll();

    CarSale createCarSale(CarSale carSale , Car car);
}
