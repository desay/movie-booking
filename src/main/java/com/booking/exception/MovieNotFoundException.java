package com.booking.exception;

/**
 * Created by Helga on 04.04.17.
 */
public class MovieNotFoundException extends BookingException {

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}

