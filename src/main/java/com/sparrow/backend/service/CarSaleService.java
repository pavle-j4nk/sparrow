package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;

public interface CarSaleService {


    CarSale createCarSale(CarSale carSale , Car car);
}
