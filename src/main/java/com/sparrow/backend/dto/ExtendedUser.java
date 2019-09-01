package com.sparrow.backend.dto;

import com.sparrow.backend.model.User;

public class ExtendedUser {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    private String role;

    private Boolean enabled;

    private Integer friendRequests;

    public ExtendedUser(User user, Integer friendRequests) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.enabled = user.getEnabled();

        this.role = user.getRole().getName();

        this.friendRequests = friendRequests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Integer friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
