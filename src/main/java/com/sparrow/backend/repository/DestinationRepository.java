package com.sparrow.backend.repository;

import com.sparrow.backend.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d from Destination d WHERE lower(d.name) LIKE %?1%")
    Destination searchByName(String name);

}
