package com.sparrow.backend.repository;

import com.sparrow.backend.model.HotelRoomDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRoomDiscountRepository extends JpaRepository<HotelRoomDiscount, Long> {
}
