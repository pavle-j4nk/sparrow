package com.sparrow.repository;

import com.sparrow.model.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraServicesRepository extends JpaRepository<ExtraService, Long> {
    ExtraService findByName(String name);
}
