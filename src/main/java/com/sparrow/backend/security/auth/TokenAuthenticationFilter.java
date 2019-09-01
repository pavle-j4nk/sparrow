package com.sparrow.backend.security.auth;

import com.sparrow.backend.security.JwtTokenProvider;
import com.sparrow.backend.security.LoggedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider provider;

    public TokenAuthenticationFilter(JwtTokenProvider providerHelper) {
        this.provider = providerHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = provider.getToken(request);
        LoggedUser loggedUser = provider.getUserFromToken(authToken);

        if (loggedUser != null) {
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(loggedUser, authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
