package com.sparrow.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EXTRA_SERVICE")
public class ExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "services")
    private Set<Hotel> hotel;

    public ExtraService() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(Set<Hotel> hotel) {
        this.hotel = hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
