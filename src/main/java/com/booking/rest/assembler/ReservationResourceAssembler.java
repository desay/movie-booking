package com.booking.rest.assembler;

import com.booking.domain.Reservation;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Helga on 04.04.17.
 */
public class ReservationResourceAssembler extends
        ResourceAssemblerSupport<Reservation, Resource> {
    public ReservationResourceAssembler(Class<?> controllerClass, Class<Resource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public Resource toResource(Reservation reservation) {
        return null;
    }
}
