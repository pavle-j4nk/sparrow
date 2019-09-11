package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.TicketDto;
import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.*;
import com.sparrow.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private TicketInviteRepository ticketInviteRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SegmentRepository segmentRepository;

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
        Flight save = flightRepository.save(flight);

/*
        for (AirplaneSegment segment : flight.getSegments()) {
            segment.setFlight(save);
            save.getSegments().add(segmentRepository.save(segment));
        }
*/

        return flightRepository.save(save);
    }

    @Override
    public Airline getAdministratedAirline(String username) {
        User user = userRepository.findByUsername(username).get();
        return airlineRepository.getFirstByAdmin(user);
    }

    @Override
    public List<Flight> search(Date departure, String from, String to) {
        Destination dstFrom = destinationRepository.searchByName(from);
        Destination dstTo = destinationRepository.searchByName(to);

        return flightRepository.findAllByFromAndToAndDepartureDate(dstFrom, dstTo, departure.toLocalDate());
    }

    @Override
    public void acceptInvite(Long id) {
        TicketInvite one = ticketInviteRepository.getOne(id);
        one.setAccepted(true);
        ticketInviteRepository.save(one);
    }

    @Override
    public void declineInvite(Long id) {
        TicketInvite one = ticketInviteRepository.getOne(id);
        one.setAccepted(false);

        Seat seat = one.getTicket().getSeat();
        seat.setAvailable(true);
        seatRepository.save(seat);

        FlightTicket ticket = one.getTicket();

        ticketInviteRepository.delete(one);
        ticketRepository.delete(ticket);
    }

    @Override
    public void createInvite(Long ticketId, String username) {
        TicketInvite ticketInvite = new TicketInvite();
        ticketInvite.setTicket(ticketRepository.getOne(ticketId));
        ticketInvite.setUser(userRepository.findByUsername(username).get());
    }

    @Override
    public FlightTicket createTicket(FlightTicket ticket, String username) {
        ticket.setUser(userRepository.findByUsername(username).get());
        ticket.getSeat().setAvailable(false);

        Seat seat = seatRepository.getOne(ticket.getSeat().getId());
        seat.setAvailable(false);
        seatRepository.save(seat);

        return ticketRepository.save(ticket);

    }

    @Override
    public List<TicketDto> getTickets(String username) {
        List<TicketDto> allByUser = ticketRepository.getAllByUser(userRepository.findByUsername(username).get())
                .stream().map(t -> new TicketDto(t, t.getSeat().getSegment().getFlight())).collect(Collectors.toList());
        return allByUser;
    }


}
