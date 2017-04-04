package com.booking.rest.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Helga on 04.04.17.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
}
