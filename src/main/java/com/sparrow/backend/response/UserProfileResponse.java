package com.sparrow.backend.response;

import com.sparrow.backend.model.User;

public class UserProfileResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Boolean enabled;

    private String role;

    public UserProfileResponse() {
    }

    public UserProfileResponse(Long id, String email, String firstName, String lastName, String address, Boolean enabled
            , String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.enabled = enabled;
        this.role = role;
    }

    public UserProfileResponse(User user) {
        this(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getAddress()
                , user.getEnabled(), user.getRole().getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
