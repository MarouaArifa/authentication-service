package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.models.Customer;
import org.sid.authenticationservice.models.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
}
