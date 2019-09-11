package com.sparrow.backend.service;

import com.sparrow.backend.dto.TicketDto;
import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Flight;
import com.sparrow.backend.model.FlightTicket;

import java.sql.Date;
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

    List<Flight> search(Date departure, String from, String to);

    void acceptInvite(Long id);

    void declineInvite(Long id);

    void createInvite(Long ticketId, String username);

    FlightTicket createTicket(FlightTicket ticket, String username);

    List<TicketDto> getTickets(String username);

}
