package com.sparrow.backend.controller;

import com.sparrow.backend.dto.AirlineDto;
import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Flight;
import com.sparrow.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PreAuthorize("hasAuthority('ROLE_AIRLINE_ADMIN')")
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
}
