package com.booking.repository;

import com.booking.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Helga on 04.04.17.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
