package com.sparrow.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {
    private String token;

    private final UserDetails principle;

    public TokenBasedAuthentication(UserDetails principle, String token) {
        super(principle.getAuthorities());
        this.principle = principle;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }

    @Override
    public boolean isAuthenticated(){
        return true;
    }
}
