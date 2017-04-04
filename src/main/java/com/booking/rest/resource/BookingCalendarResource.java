package com.booking.rest.resource;

import com.booking.BookingCalendar;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Helga on 04.04.17.
 */
@RestController
@ExposesResourceFor(BookingCalendar.class)
@RequestMapping(value = "/calendars", produces = APPLICATION_JSON_VALUE)
public class BookingCalendarResource {
}
