package com.booking.repository;

import com.booking.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Helga on 04.04.17.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
