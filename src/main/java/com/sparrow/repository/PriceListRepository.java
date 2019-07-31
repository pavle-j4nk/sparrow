package com.sparrow.repository;

import com.sparrow.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    PriceList findByHotelId(Long id);
}
