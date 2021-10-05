package org.sid.authenticationservice.config.repository;

import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContactBaseRepository<C, L extends Number> extends JpaRepository<Contact, Long>{
}
