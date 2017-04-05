package com.booking.rest.resource;

import com.booking.domain.Reservation;
import com.booking.exception.BookingException;
import com.booking.rest.ErrorResponse;
import com.booking.rest.assembler.ReservationResourceAssembler;
import com.booking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Helga on 04.04.17.
 */
@RepositoryRestController
public class ReservationResource {
    private final ReservationService reservationService;
    private final ReservationResourceAssembler resourceAssembler;
    private final Environment environment;

    @Autowired
    public ReservationResource(ReservationService reservationService,
                               ReservationResourceAssembler resourceAssembler, Environment environment) {
        this.reservationService = reservationService;
        this.resourceAssembler = resourceAssembler;
        this.environment = environment;
    }
    @RequestMapping(value = "/reservations", method = POST)
    public ResponseEntity<?> createReservation(
            @RequestBody final Resource<Reservation> bookingRequest) {
        try {
            Reservation reservation = reservationService.save(bookingRequest.getContent());
            return success(CREATED, reservation);
        } catch (BookingException e) {
            return error(CONFLICT, e);
        }
    }

    @RequestMapping(value = "/reservations/{reservationId}", method = PUT)
    public ResponseEntity<?> updateReservation(
            @RequestBody final Resource<Reservation> bookingRequest,
            @PathVariable("reservationId") Long reservationId) {
        if (!isReservationExist(reservationId)) {
            return ResponseEntity.status(NOT_FOUND)
                    .build();
        }
        try {
            Reservation requestReservation = bookingRequest.getContent();
            requestReservation.setId(reservationId);
            Reservation reservation = reservationService.save(requestReservation);
            return success(OK, reservation);
        } catch (BookingException e) {
            return error(CONFLICT, e);
        }
    }

    private boolean isReservationExist(Long reservationId) {
        return reservationService.get(reservationId) != null;
    }

    private ResponseEntity<Resource<Reservation>> success(HttpStatus status,
                                                          Reservation reservation) {
        return ResponseEntity.status(status)
                .body(resourceAssembler.toResource(reservation));
    }

    private ResponseEntity<ErrorResponse<String>> error(HttpStatus status, Exception e) {
        String errorMessage = null;
        if (e.getMessage() != null) {
            errorMessage = environment.getProperty(e.getMessage());
        }
        return ResponseEntity.status(status)
                .body(ErrorResponse.anErrorMessage(errorMessage));
    }

}