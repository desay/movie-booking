package com.booking.rest.assembler;

import com.booking.BookingCalendar;
import com.booking.domain.Movie;
import com.booking.domain.Reservation;
import com.booking.rest.resource.BookingCalendarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Helga on 04.04.17.
 */
@Component
public class BookingCalendarResourceAssembler extends
        ResourceAssemblerSupport<BookingCalendar, Resource> {
    private final EntityLinks entityLinks;
    private final ReservationResourceAssembler reservationResourceAssembler;
    private final ControllerEntityLinks controllerEntityLinks;

    @Autowired
    public BookingCalendarResourceAssembler(EntityLinks entityLinks,
                                            ReservationResourceAssembler reservationResourceAssembler,
                                            ControllerEntityLinks controllerEntityLinks) {
        super(BookingCalendarResource.class, Resource.class);
        this.entityLinks = entityLinks;
        this.reservationResourceAssembler = reservationResourceAssembler;
        this.controllerEntityLinks = controllerEntityLinks;
    }

    @Override
    public Resource toResource(BookingCalendar bookingCalendar) {
        Resource resource = createResourceWithId(bookingCalendar.getMovieId(), bookingCalendar);
        resource.add(entityLinks.linkToSingleResource(Movie.class, bookingCalendar.getMovieId()));
        return resource;
    }

    public Resources<Resource> toResources(Collection<BookingCalendar> bookingCalendars) {
        final ArrayList<Resource> resources = new ArrayList<>();
        bookingCalendars.forEach(bc -> resources.add(toResource(bc)));
        Link selfRel = controllerEntityLinks.linkToCollectionResource(BookingCalendar.class)
                .withSelfRel();
        return new Resources<>(resources, selfRel);
    }

    @Override
    protected Resource instantiateResource(BookingCalendar bookingCalendar) {
        Map<LocalDate, List<Resource<Reservation>>> bc = bookingCalendar.getBookings().entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, listReservationMapper(), throwingMerger(), TreeMap::new));
        return new Resource<>(bc);
    }

    private Function<Map.Entry<LocalDate, List<Reservation>>, List<Resource<Reservation>>> listReservationMapper() {
        return calendarEntry -> calendarEntry.getValue()
                .stream()
                .map(reservationResourceAssembler::toResource)
                .collect(toList());
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }


}
