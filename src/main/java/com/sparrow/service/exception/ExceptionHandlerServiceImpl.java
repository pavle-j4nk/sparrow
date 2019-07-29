package com.sparrow.service.exception;

import com.sparrow.service.ExceptionHandlerService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerServiceImpl implements ExceptionHandlerService {
    private static Integer ERR_CODE_DEFAULT = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    @Override
    public void handle(Exception e, HttpServletResponse response) {
        int sc = ERR_CODE_DEFAULT;

        if (e instanceof BadRequestException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (e instanceof BadCredentialsException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        } else if (e instanceof NotFoundException) {
            sc = HttpServletResponse.SC_BAD_REQUEST;
        }

        try {
            response.sendError(sc, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
