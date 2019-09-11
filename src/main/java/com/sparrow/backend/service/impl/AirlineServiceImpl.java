package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Flight;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.AirlineRepository;
import com.sparrow.backend.repository.FlightRepository;
import com.sparrow.backend.repository.UserRepository;
import com.sparrow.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public void delete(Long id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public Airline getOne(Long id) {
        return airlineRepository.getOne(id);
    }

    @Override
    public List<Flight> getFlights(Long airlineId) {
        return airlineRepository.getOne(airlineId).getFlights();
    }

    @Override
    public Flight getFlight(Long id) {
        return flightRepository.getOne(id);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Airline getAdministratedAirline(String username) {
        User user = userRepository.findByUsername(username).get();
        return airlineRepository.getFirstByAdmin(user);
    }

}
