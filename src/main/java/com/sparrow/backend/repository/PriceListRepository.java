package com.sparrow.backend.repository;

import com.sparrow.backend.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    PriceList findByHotelId(Long id);
}
