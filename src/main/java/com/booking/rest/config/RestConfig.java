package com.booking.rest.config;

import com.booking.domain.Customer;
import com.booking.domain.Movie;
import com.booking.domain.Reservation;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

/**
 * Created by Helga on 04.04.17.
 */
@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.exposeIdsFor(Reservation.class, Customer.class, Movie.class);
    }
}