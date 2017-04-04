package com.booking.repository;

import com.booking.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Helga on 04.04.17.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMovieId(@Param("movieId") Long movieId);

    List<Reservation> findByMovieIdOrderByStartDateAsc(@Param("movieId") Long movieId);

    List<Reservation> findByMovieIdAndStartDateBetween(@Param("movieId") Long movieId,
                                                      @Param("startDate") LocalDateTime startDate,
                                                      @Param("endDate") LocalDateTime endDate);

    List<Reservation> findOverlapped(@Param("movieId") Long movieId,
                                     @Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);

}
