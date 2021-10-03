package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NaturalPersonRepository  extends ContactBaseRepository<Contact> {
}
