package com.sparrow.controller;

import com.sparrow.dto.JwtAuthenticationRequest;
import com.sparrow.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;
//    private final ExceptionHandlerService exceptionHandlerService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
//        this.exceptionHandlerService = exceptionHandlerService;
    }

    @PostMapping
    public ResponseEntity createAuthenticationToken(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    @RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.performAuthentication(httpServletRequest,
                httpServletResponse, authenticationRequest));
    }

    /**
     * NOTE: FOR TESTING PURPOSES ONLY
     */
    @GetMapping
    @Deprecated
    public ResponseEntity login(
            HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , @RequestParam("username") String username
            , @RequestParam("password") String password)
            throws AuthenticationException {

        JwtAuthenticationRequest authenticationRequest = new JwtAuthenticationRequest(username, password);

        return ResponseEntity.ok(authenticationService.performAuthentication(httpServletRequest,
                httpServletResponse, authenticationRequest));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity refreshAuthenticationToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return ResponseEntity.ok(authenticationService.refresh(httpServletRequest, httpServletResponse));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok("");
    }

    @ExceptionHandler
    public void onException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        exceptionHandlerService.onException(e, response, log);
    }

}
