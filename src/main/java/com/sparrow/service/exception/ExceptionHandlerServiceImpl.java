package com.sparrow.service.exception;

import com.sparrow.service.ExceptionHandlerService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionHandlerServiceImpl implements ExceptionHandlerService {
    private static Integer ERR_CODE_DEFAULT = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    private static Map<Class<? extends Exception>, Integer> errCodes = new HashMap<>();

    static {
        errCodes.put(BadRequestException.class, HttpServletResponse.SC_BAD_REQUEST);
        errCodes.put(BadCredentialsException.class, HttpServletResponse.SC_BAD_REQUEST);
        errCodes.put(NotFoundException.class, HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void handle(Exception e, HttpServletResponse response) {
        try {
            response.sendError(errCodes.getOrDefault(e.getClass(), ERR_CODE_DEFAULT), e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
