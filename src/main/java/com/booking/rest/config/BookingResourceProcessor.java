package com.booking.rest.config;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 04.04.17.
 */
@Component
public class BookingResourceProcessor implements
        ResourceProcessor<RepositoryLinksResource> {
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource repositoryLinksResource) {
        return null;
    }
}
