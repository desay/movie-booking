package com.booking.rest.resource;

import com.booking.BookingCalendar;
import com.booking.exception.MovieNotFoundException;
import com.booking.rest.assembler.BookingCalendarResourceAssembler;
import com.booking.service.BookingCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Helga on 04.04.17.
 */
@RestController
@ExposesResourceFor(BookingCalendar.class)
@RequestMapping(value = "/calendars", produces = APPLICATION_JSON_VALUE)
public class BookingCalendarResource {
    private final BookingCalendarService calendarService;
    private final BookingCalendarResourceAssembler resourceAssembler;

    @Autowired
    public BookingCalendarResource(BookingCalendarService bookingCalendarService,
                                   BookingCalendarResourceAssembler resourceAssembler) {
        this.calendarService = bookingCalendarService;
        this.resourceAssembler = resourceAssembler;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<Resource>> getAll() {
        List<BookingCalendar> calendars = calendarService.buildBookingCalendars();
        return ok(resourceAssembler.toResources(calendars));
    }

    @RequestMapping(value = "/{movieId}", method = GET)
    public ResponseEntity<Resource> getOne(@PathVariable("movieId") Long movieId) {
        try {
            BookingCalendar bookingCalendar = calendarService.buildBookingCalendar(movieId);
            return ok(resourceAssembler.toResource(bookingCalendar));
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }
}
