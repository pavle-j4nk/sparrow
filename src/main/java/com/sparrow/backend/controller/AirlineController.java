package com.sparrow.backend.controller;

import com.sparrow.backend.dto.AirlineDto;
import com.sparrow.backend.dto.TicketDto;
import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Flight;
import com.sparrow.backend.model.FlightTicket;
import com.sparrow.backend.service.AirlineService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/airline")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Airline> getAll() {
        return airlineService.getAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Airline create(@RequestBody Airline airline) {
        return airlineService.save(airline);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_AIRLINE_ADMIN')")
    public Airline update(@RequestBody Airline airline) {
        return airlineService.save(airline);
    }

    @GetMapping("/{id}/flights")
    @PreAuthorize("hasAuthority('ROLE_AIRLINE_ADMIN')")
    public List<Flight> getFlights(@PathVariable Long id) {
        return airlineService.getFlights(id);
    }

    @GetMapping("/flight/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return airlineService.getFlight(id);
    }

    @PostMapping("/{id}/flight")
    @PreAuthorize("hasAuthority('ROLE_AIRLINE_ADMIN')")
    public Flight saveFlight(@PathVariable Long id, @RequestBody Flight flight) {
        flight.setAirline(new Airline());
        flight.getAirline().setId(id);

        return airlineService.saveFlight(flight);
    }

    @GetMapping("/administrated")
    @PreAuthorize("hasAuthority('ROLE_AIRLINE_ADMIN')")
    public Airline getAdministratedAirline(Principal principal) {
        return airlineService.getAdministratedAirline(principal.getName());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        airlineService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AirlineDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new AirlineDto(airlineService.getOne(id)));
    }

    @GetMapping("search")
    public ResponseEntity<List<Flight>> search(@RequestParam Date departure, @RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(airlineService.search(departure, from, to));
    }

    @PostMapping("ticket/invite/accept/{id}")
    public ResponseEntity<?> acceptInvite(@PathVariable Long id) {
        airlineService.acceptInvite(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("ticket/invite/decline/{id}")
    public ResponseEntity<?> declineInvite(@PathVariable Long id) {
        airlineService.declineInvite(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ticket")
    public ResponseEntity<FlightTicket> createTicket(@RequestBody FlightTicket ticket, Principal principal) {
        return ResponseEntity.ok(airlineService.createTicket(ticket, principal.getName()));
    }

    @PostMapping("ticket/invite/{tid}/{username}")
    public ResponseEntity<?> createInvite(@PathVariable Long tid, @PathVariable String username) {
        airlineService.createInvite(tid, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketDto>> getTickets(Principal principal) {
        return ResponseEntity.ok(airlineService.getTickets(principal.getName()));
    }

}
