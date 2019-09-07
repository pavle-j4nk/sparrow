package com.sparrow.backend.repository;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentACarRepository extends JpaRepository<RentACar , Long> {

    Optional<RentACar> findByName(String name);

    Optional<RentACar> findByAddress(String address);

}
