package com.sparrow.backend.model;

import javax.persistence.*;

@Entity
public class TicketInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    @ManyToOne
    private FlightTicket ticket;

    private Boolean accepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FlightTicket getTicket() {
        return ticket;
    }

    public void setTicket(FlightTicket ticket) {
        this.ticket = ticket;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
