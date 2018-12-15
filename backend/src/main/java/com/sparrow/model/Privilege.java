package com.sparrow.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Privilege {
    public static final String USER_PROFILE_EDIT = "USER_PROFILE_EDIT";
    public static final String AIRLINE_PROFILE_EDIT = "AIRLINE_PROFILE_EDIT";



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege() {
        System.out.println("slack test");
    }

    public Privilege(String name) {
        this.name = name;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
