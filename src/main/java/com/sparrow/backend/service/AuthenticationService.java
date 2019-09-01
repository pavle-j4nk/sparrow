package com.sparrow.backend.service;

import com.sparrow.backend.dto.JwtAuthenticationRequest;
import com.sparrow.backend.dto.UserTokenState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    UserTokenState performAuthentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                         JwtAuthenticationRequest authRequest);

    UserTokenState refresh(HttpServletRequest request, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

}
