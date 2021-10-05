package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ContactRepository extends ContactBaseRepository<Contact, Long> {

    Optional<Contact> findById(Long id);


}
