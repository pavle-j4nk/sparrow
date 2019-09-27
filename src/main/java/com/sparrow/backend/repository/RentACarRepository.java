package com.sparrow.backend.repository;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface RentACarRepository extends JpaRepository<RentACar , Long> {

    RentACar findById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    RentACar save(RentACar rentACar);

    Optional<RentACar> findByName(String name);

    Optional<RentACar> findByAddress(String address);

    List<RentACar> findAllByCity(String string);
}
