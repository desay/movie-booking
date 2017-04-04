package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.exception.BookingException;

import javax.validation.Valid;

public interface ReservationService {
    Reservation save(@Valid Reservation reservation) throws BookingException;

    Reservation get(Long id);
}
