package com.sparrow.backend.repository;

import com.sparrow.backend.model.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
}
