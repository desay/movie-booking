package com.booking.rest.assembler;

import com.booking.BookingCalendar;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 04.04.17.
 */
@Component
public class BookingCalendarResourceAssembler extends
        ResourceAssemblerSupport<BookingCalendar, Resource> {
    public BookingCalendarResourceAssembler(Class<?> controllerClass, Class<Resource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public Resource toResource(BookingCalendar bookingCalendar) {
        return null;
    }
}
