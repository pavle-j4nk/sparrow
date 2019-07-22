package com.sparrow.service.auth;

import com.sparrow.dto.auth.JwtAuthenticationRequest;
import com.sparrow.dto.auth.UserTokenState;
import com.sparrow.model.user.User;
import com.sparrow.security.JwtTokenProvider;
import com.sparrow.security.LoggedUser;
import com.sparrow.service.auth.exception.TokenNotRefreshable;
import com.sparrow.service.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String TOKEN_COOKIE_NAME = "token";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public UserTokenState performAuthentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                                JwtAuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        String jwt = tokenUtils.generateToken(user);
        putTokenCookie(httpRequest, httpResponse, jwt);

        return new UserTokenState(jwt, tokenUtils.getExpirationDateFromToken(jwt));
    }

    @Override
    public UserTokenState refresh(HttpServletRequest request, HttpServletResponse response) {
        String jwt = tokenUtils.getToken(request);
        LoggedUser loggedUser = tokenUtils.getUserFromTokenUnsafe(jwt);

        if (loggedUser == null) {
            throw new TokenNotRefreshable("User is not logged.");
        }

        UserDetails user = userDetailsService.loadUserByUsername(loggedUser.getUsername());
        if (!user.isEnabled()) {
            throw new TokenNotRefreshable("User is disabled");
        }

        String refreshed = tokenUtils.generateToken(user);
        putTokenCookie(request, response, refreshed);

        return new UserTokenState(refreshed, tokenUtils.getExpirationDateFromToken(refreshed));
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        Cookie cookie = getCookie(httpServletRequest, TOKEN_COOKIE_NAME);
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
    }

    private void putTokenCookie(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String jwt) {
        Cookie requestCookie = getCookie(httpRequest, TOKEN_COOKIE_NAME);
        Cookie tokenCookie;
        if (requestCookie != null) {
            tokenCookie = requestCookie;
            tokenCookie.setValue(jwt);
        } else {
            tokenCookie = new Cookie(TOKEN_COOKIE_NAME, jwt);
        }

        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        httpResponse.addCookie(tokenCookie);
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

}
