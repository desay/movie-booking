package com.booking.service;

import com.booking.BookingCalendar;
import com.booking.exception.MovieNotFoundException;

import java.util.List;

/**
 * Created by Helga on 04.04.17.
 */
public interface BookingCalendarService {

    BookingCalendar buildBookingCalendar(Long movieId) throws MovieNotFoundException;

    List<BookingCalendar> buildBookingCalendars();
}
