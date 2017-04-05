package com.booking.rest.assembler;

import com.booking.domain.Customer;
import com.booking.domain.Movie;
import com.booking.domain.Reservation;
import com.booking.rest.resource.ReservationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Helga on 04.04.17.
 */
public class ReservationResourceAssembler extends
        ResourceAssemblerSupport<Reservation, Resource> {
    private final EntityLinks entityLinks;

    @Autowired
    public ReservationResourceAssembler(EntityLinks entityLinks) {
        super(ReservationResource.class, Resource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<Reservation> toResource(Reservation reservation) {
        Link self = entityLinks.linkToSingleResource(Reservation.class, reservation.getId())
                .withSelfRel();
        Link movie = entityLinks.linkToSingleResource(Movie.class, reservation.getMovie()
                .getId());
        Link customer = entityLinks.linkToSingleResource(Customer.class, reservation.getCustomer()
                .getId());
        return new Resource<>(reservation, self, movie, customer);
    }
}