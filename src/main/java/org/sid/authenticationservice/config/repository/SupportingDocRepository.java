package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportingDocRepository  extends JpaRepository<SupportingDocument,Long> {
}
