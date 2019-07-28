package com.sparrow.service;

import javax.servlet.http.HttpServletResponse;

public interface ExceptionHandlerService {

    void handle(Exception e, HttpServletResponse response);

}
