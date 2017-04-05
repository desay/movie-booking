package com.booking.service;

import com.booking.domain.Customer;
import com.booking.domain.Movie;
import com.booking.domain.Reservation;
import com.booking.exception.BookingException;
import com.booking.repository.CustomerRepository;
import com.booking.repository.MovieRepository;
import com.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Helga on 04.04.17.
 */
@Service
@Validated
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  MovieRepository movieRepository,
                                  CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Reservation get(Long id) {
        return reservationRepository.findOne(id);
    }

    @Override
    public Reservation save(final Reservation reservation) throws BookingException {
        checkThatMovieAndCustomerExist(reservation);
        checkThatReservationFitsMovieWorkingTime(reservation);
        checkThatReservationFitsWithOtherReservations(reservation);
        return reservationRepository.save(reservation);
    }

    private void checkThatMovieAndCustomerExist(Reservation reservation) throws BookingException {
        String customerErrorMessage = "booking.restrictions.customerDoesntExist";
        String movieErrorMessage = "booking.restrictions.movieDoesntExist";
        checkThatEntityExist(customerRepository, reservation.getCustomer()
                .getId(), customerErrorMessage);
        checkThatEntityExist(movieRepository, reservation.getMovie()
                .getId(), movieErrorMessage);
    }

    private void checkThatEntityExist(JpaRepository<?, Long> repository, Long id, String message)
            throws BookingException {
        if (repository.findOne(id) == null) {
            throw new BookingException(message);
        }
    }

    private void checkThatReservationFitsWithOtherReservations(final Reservation reservation)
            throws BookingException {
        List<Reservation> reservations = fetchReservations(reservation);

        if (reservations.isEmpty()) {
            return;
        }
        if (isReservationOneAndItself(reservation, reservations)) {
            return;
        }
        throw new BookingException("booking.restrictions.overlapReservations");
    }

    private boolean isReservationOneAndItself(Reservation reservation,
                                              List<Reservation> reservations) {
        if (reservations.size() > 1) {
            return false;
        }
        Reservation existingReservation = reservations.get(0);
        return !(reservation.getId() == null || existingReservation.getId() == null)
                && existingReservation.getId()
                .equals(reservation.getId());
    }

    private void checkThatReservationFitsMovieWorkingTime(Reservation reservation)
            throws BookingException {
        Movie movie = movieRepository.findOne(reservation.getMovie()
                .getId());
        LocalTime reservationStart = reservation.getStartDate()
                .toLocalTime();
        LocalTime movieStart = movie.getStartTime();
        LocalTime reservationEnd = reservation.getEndDate()
                .toLocalTime();
        LocalTime movieEnd = movie.getEndTime();

        if (reservationStart.isBefore(movieStart) || reservationStart.isAfter(movieEnd)
                || reservationEnd.isBefore(movieStart) || reservationEnd.isAfter(movieEnd)) {
            throw new BookingException("booking.restrictions.movieWorkingTime");
        }
    }

    private List<Reservation> fetchReservations(Reservation reservation) {
        return reservationRepository.findOverlapped(reservation.getMovie()
                .getId(), reservation.getStartDate(), reservation.getEndDate());
    }

}
