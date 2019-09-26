package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;
import com.sparrow.backend.repository.CarSaleRepository;
import com.sparrow.backend.service.CarSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarSaleServiceImpl implements CarSaleService {

    @Autowired
    CarSaleRepository carSaleRepository;

    @Override
    @Transactional
    public CarSale createCarSale(CarSale carSale , Car car) {

        CarSale carSaleInput = new CarSale();
        carSaleInput.setCar(car);
        carSaleInput.setStart(carSale.getStart());
        carSaleInput.setEnd(carSale.getEnd());
        carSaleInput.setPrice(carSale.getPrice());

        return carSaleRepository.save(carSaleInput);
    }
}
