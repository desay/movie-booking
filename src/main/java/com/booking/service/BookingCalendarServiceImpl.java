package com.booking.service;

import com.booking.BookingCalendar;
import com.booking.domain.Movie;
import com.booking.exception.MovieNotFoundException;
import com.booking.repository.MovieRepository;
import com.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 04.04.17.
 */
public class BookingCalendarServiceImpl implements BookingCalendarService {
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public BookingCalendarServiceImpl(MovieRepository movieRepository,
                                      ReservationRepository reservationRepository) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookingCalendar buildBookingCalendar(Long movieId) throws MovieNotFoundException {
        if (movieRepository.findOne(movieId) == null) {
            throw new MovieNotFoundException();
        }
        return buildBookingCalendarInternal(movieId);
    }

    @Override
    public List<BookingCalendar> buildBookingCalendars() {
        final ArrayList<BookingCalendar> calendars = new ArrayList<>();
        final List<Movie> movies = movieRepository.findAll();

        movies.forEach(movie -> {
            BookingCalendar calendar = buildBookingCalendarInternal(movie.getId());
            calendars.add(calendar);
        });

        return calendars;
    }

    private BookingCalendar buildBookingCalendarInternal(Long movieId) {
        final BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setMovieId(movieId);
        reservationRepository.findByMovieIdOrderByStartDateAsc(movieId)
                .forEach(bookingCalendar::putReservation);
        return bookingCalendar;
    }

}
