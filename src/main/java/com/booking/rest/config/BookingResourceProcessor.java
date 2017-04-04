package com.booking.rest.config;

import com.booking.rest.resource.BookingCalendarResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 04.04.17.
 */
@Component
public class BookingResourceProcessor implements
        ResourceProcessor<RepositoryLinksResource> {
    private final ControllerEntityLinks controllerEntityLinks;

    @Autowired
    public BookingResourceProcessor(ControllerEntityLinks controllerEntityLinks) {
        this.controllerEntityLinks = controllerEntityLinks;
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(BookingCalendarResource.class).withRel("calendars"));
        return resource;
    }
}
