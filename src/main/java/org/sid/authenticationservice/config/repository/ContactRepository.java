package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ContactRepository extends ContactBaseRepository<Contact, Long> {

    Optional<Contact> findById(Long id);

    @Modifying
    @Transactional
    @Query("delete from Contact c where c.idUser.id =:id")
    int deleteContact(Long id);


}
