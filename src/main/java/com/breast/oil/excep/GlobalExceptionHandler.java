package com.breast.oil.excep;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientAbortException.class)
    @ResponseBody
    public void connectionAbort(final ClientAbortException e) {
        // Tomcat-specific exception when existing request is aborted by client

        return;
    }
}
