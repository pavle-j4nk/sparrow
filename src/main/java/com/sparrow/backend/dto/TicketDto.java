package com.sparrow.backend.dto;

import com.sparrow.backend.model.Flight;
import com.sparrow.backend.model.FlightTicket;

public class TicketDto {
    private FlightTicket ticket;
    private Flight flight;

    public TicketDto() {
    }

    public TicketDto(FlightTicket ticket, Flight flight) {
        this.ticket = ticket;
        this.flight = flight;
    }

    public FlightTicket getTicket() {
        return ticket;
    }

    public void setTicket(FlightTicket ticket) {
        this.ticket = ticket;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
