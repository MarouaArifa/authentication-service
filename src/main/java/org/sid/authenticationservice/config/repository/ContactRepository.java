package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Contact;
import org.sid.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ContactRepository extends ContactBaseRepository<Contact> {



}
