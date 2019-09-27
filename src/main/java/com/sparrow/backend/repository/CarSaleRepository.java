package com.sparrow.backend.repository;

import com.sparrow.backend.model.CarSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.sql.Date;
import java.util.List;

public interface CarSaleRepository extends JpaRepository<CarSale, Long> {

    @Query("SELECT c from CarSale c where(c.start <= :start AND c.end >= :end)")
    List<CarSale> findByDate(@Param("start") Date start , @Param("end") Date end) ;

    @Lock(LockModeType.PESSIMISTIC_READ)
    CarSale save(CarSale carSale);
}
