package com.sparrow.backend.repository;

import com.sparrow.backend.model.AirplaneSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<AirplaneSegment, Long> {
}
