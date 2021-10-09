package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.Contact;
import org.sid.authenticationservice.models.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {
    Optional<NaturalPerson> findById(Long id);
    NaturalPerson findByCin(String cin);
}
