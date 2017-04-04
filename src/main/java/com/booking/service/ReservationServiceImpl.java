package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.exception.BookingException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created by Helga on 04.04.17.
 */
@Service
@Validated
public class ReservationServiceImpl implements ReservationService {
    @Override
    public Reservation save(@Valid Reservation reservation) throws BookingException {
        return null;
    }

    @Override
    public Reservation get(Long id) {
        return null;
    }
}
