package com.sparrow.backend.repository;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentACarRepository extends JpaRepository<RentACar , Long> {

    RentACar findById(Integer id);

    Optional<RentACar> findByName(String name);

    Optional<RentACar> findByAddress(String address);

    /*TODO : CASE NOT SENSITIVE QUERY*/
    List<RentACar> findAllByCity(String string);
}
