package com.sparrow.backend.service;

import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Flight;

import java.util.List;

public interface AirlineService {

    List<Airline> getAll();

    Airline save(Airline airline);

    void delete(Long id);

    Airline getOne(Long id);

    List<Flight> getFlights(Long airlineId);

    Flight getFlight(Long id);

    Flight saveFlight(Flight flight);

    Airline getAdministratedAirline(String username);

}
