package com.sparrow.service.auth;

import com.sparrow.dto.auth.JwtAuthenticationRequest;
import com.sparrow.dto.auth.UserTokenState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    UserTokenState performAuthentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                         JwtAuthenticationRequest authRequest);

    UserTokenState refresh(HttpServletRequest request, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

}
