package org.sid.authenticationservice.config.repository;

import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContactBaseRepository <T extends Contact> extends JpaRepository<T,Long> {
}
