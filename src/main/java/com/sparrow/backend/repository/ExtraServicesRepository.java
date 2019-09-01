package com.sparrow.backend.repository;

import com.sparrow.backend.model.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraServicesRepository extends JpaRepository<ExtraService, Long> {
    ExtraService findByName(String name);
}
