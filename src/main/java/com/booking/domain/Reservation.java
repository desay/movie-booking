package com.booking.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Helga on 04.04.17.
 */
@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = "Reservation.findOverlapped",
        query = "select r from Reservation r where (r.room.id = :roomId)" +
                "and ((r.startDate >= :startDate and r.startDate <= :endDate) or" +
                "(r.endDate > :startDate and r.endDate <= :endDate))")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "validation.Reservation.startDate.notnull")
    private LocalDateTime startDate;

    @Column(nullable = false)
    @NotNull(message = "validation.Reservation.duration.notnull")
    private LocalTime duration;

    @ManyToOne(optional = false)
    @NotNull(message = "validation.Reservation.customer.notnull")
    private Customer customer;

    @ManyToOne(optional = false)
    @NotNull(message = "validation.Reservation.movie.notnull")
    private Movie movie;

    private LocalDateTime submissionDate;

    public Reservation() {
        submissionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Access(AccessType.PROPERTY)
    public LocalDateTime getEndDate() {
        if (startDate == null || duration == null) {
            return null;
        }
        return startDate.plusHours(duration.getHour())
                .plusMinutes(duration.getMinute());
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
