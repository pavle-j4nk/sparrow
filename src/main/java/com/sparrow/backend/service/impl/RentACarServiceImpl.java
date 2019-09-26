package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.CarSearchDto;
import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.dto.RentACarSearchDto;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.Dealership;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.repository.CarRepository;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.repository.RentACarRepository;
import com.sparrow.backend.service.RentACarService;
import com.sparrow.backend.service.exception.RentacarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RentACarServiceImpl implements RentACarService {
    @Autowired
    RentACarRepository rentACarRepository;

    @Autowired
    CarReservationRepository carReservationRepository;

    @Override
    public List<RentACar> findAll() {
        return rentACarRepository.findAll();
    }

    @Override
    public List<RentACarDto> findAllExtended() {
        List<RentACar> rentACars = findAll();
        List<RentACarDto> rentACarDtos = new ArrayList<>();
        for(RentACar r : rentACars){
            RentACarDto dto = new RentACarDto();

            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setAddress(r.getAddress());
            dto.setDescription(r.getDescription());
            dto.setAdmin(r.getAdmin());
            dto.setDealerships(r.getDealerships());
            dto.setCars(r.getCars());
            dto.setCity(r.getCity());
            rentACarDtos.add(dto);
        }
        return rentACarDtos;
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
            throw new RentacarNotFoundException(id);
        }
    }

    @Override
    public RentACar findByName(String name) {
        Optional<RentACar> rentACar = rentACarRepository.findByName(name);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            throw new RentacarNotFoundException(name);
        }
    }

    @Override
    public RentACar findByAddress(String address) {
        Optional<RentACar> rentACar = rentACarRepository.findByAddress(address);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            throw new RentacarNotFoundException(address);
        }
    }

    @Override
    public RentACar findById(Long id) {
        Optional<RentACar> rentACar = rentACarRepository.findById(id);
        if(rentACar.isPresent()) {
            return rentACar.get();
        }else{
            throw new RentacarNotFoundException(id);
        }
    }

    @Override
    public RentACar save(RentACar rentACar) {
       return rentACarRepository.save(rentACar);
}

    @Override
    public RentACar update(RentACar rentACar , Long rentacarId) {
        Optional<RentACar> hotelOptional = rentACarRepository.findById(rentACar.getId());
        RentACar carForReturn = hotelOptional.get();

        carForReturn.setDescription(rentACar.getDescription());
        carForReturn.setAddress(rentACar.getAddress());
        carForReturn.setName(rentACar.getName());


        return  rentACarRepository.save(carForReturn);
    }

    @Override
    public void delete(RentACar rentACar) {
    rentACarRepository.delete(rentACar);
    }

    @Override
    public RentACar create(RentACar newRentacarDto) {
        /*TODO : IMPLEMENT CREATE UPDATE DEALERSHIP AND SEARCH*/
        return null;
    }

    @Override
    public RentACar updateDealership(Long rentACarId, Long dealershipId, Dealership dealership) {
        return null;
    }

    @Override
    public List<RentACar> search(RentACarSearchDto rentACarSearchDto) {
       List<RentACar> rentACars = rentACarRepository.findAllByCity(rentACarSearchDto.getCity());

       return rentACars;
    }

    @Override
    public Set<Car> searchCars(CarSearchDto carSearchDto, Long rentacarId) {


        RentACar rentACar = findById(rentacarId);
        Set<Car> carsAll  = rentACar.getCars();

        List<CarReservation> reservations = carReservationRepository.findByDate(carSearchDto.getStart(), carSearchDto.getEnd());

        for(CarReservation reservation : reservations){
            for(Car cars : reservation.getCars()){
                carsAll.removeIf(c -> c.getId().equals(cars.getId()));
           }
        }

        carsAll.removeIf(c -> (c.getSeats() < carSearchDto.getSeats()));
        carsAll.removeIf(c-> (c.getPrice() > carSearchDto.getPriceLow()));

        return carsAll;
    }
}
