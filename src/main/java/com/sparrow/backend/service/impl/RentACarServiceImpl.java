package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.model.Dealership;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.repository.RentACarRepository;
import com.sparrow.backend.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentACarServiceImpl implements RentACarService {
    @Autowired
    RentACarRepository rentACarRepository;

    @Override
    public List<RentACar> findAll() {
        return rentACarRepository.findAll();
    }

    @Override
    public List<RentACarDto> findAllExtended() {
        return null; /*TODO : implemen find all extended*/
    }

    @Override
    public RentACar getOne(Long id) {
        return rentACarRepository.getOne(id);
    }

    @Override
    public RentACar findOne(Long id) {
        Optional<RentACar> rentACar = rentACarRepository.findById(id);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            return null; /* TODO: IMPLEMENTIRATI RENTACAR NOT FOUND*/
        }
    }

    @Override
    public RentACar findByName(String name) {
        Optional<RentACar> rentACar = rentACarRepository.findByName(name);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            return null; /* TODO: IMPLEMENTIRATI RENTACAR NOT FOUND*/
        }
    }

    @Override
    public RentACar findByAddress(String address) {
        Optional<RentACar> rentACar = rentACarRepository.findByAddress(address);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            return null; /* TODO: IMPLEMENTIRATI RENTACAR NOT FOUND*/
        }
    }

    @Override
    public RentACar findById(Long id) {
        Optional<RentACar> rentACar = rentACarRepository.findById(id);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            return null; /* TODO: IMPLEMENTIRATI RENTACAR NOT FOUND*/
        }
    }

    @Override
    public RentACar save(RentACar rentACar) {
       return rentACarRepository.save(rentACar);
}

    @Override
    public RentACar update(RentACar rentACar) {
        return null; /*TODO : IMPLEMENTIRATI UPDATE*/
    }

    @Override
    public void delete(RentACar rentACar) {
    rentACarRepository.delete(rentACar);
    }

    @Override
    public RentACar create(RentACar newHotelDto) {
        /*TODO : IMPLEMENT CREATE UPDATE DEALERSHIP AND SEARCH*/
        return null;
    }

    @Override
    public RentACar updateDealership(Long rentACarId, Long dealershipId, Dealership dealership) {
        return null;
    }

    @Override
    public List<RentACar> search(RentACarDto hotelSearchDto) {
        return null;
    }
}
