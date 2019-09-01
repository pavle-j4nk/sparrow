package com.sparrow.backend.repository;

import com.sparrow.backend.model.HotelServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServicesRepository extends JpaRepository<HotelServices, Long> {
    HotelServices findByExtraServiceId(Long id);
}
