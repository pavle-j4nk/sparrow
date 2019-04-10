package com.sparrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparrow.model.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Role {
    public static final String USER = "USER";
    public static final String AIRLINE_ADMIN = "AIRLINE_ADMIN";
    public static final String HOTEL_ADMIN = "HOTEL_ADMIN";
    public static final String RENT_A_CAR_ADMIN = "RENT_A_CAR_ADMIN";
    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "role")
    @JsonIgnore
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Collection<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
