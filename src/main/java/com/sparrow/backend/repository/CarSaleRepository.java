package com.sparrow.backend.repository;

import com.sparrow.backend.model.CarSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface CarSaleRepository extends JpaRepository<CarSale, Long> {


    @Lock(LockModeType.PESSIMISTIC_READ)
    CarSale save(CarSale carSale);
}
