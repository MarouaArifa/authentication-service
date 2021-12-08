package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.SupportingDocument;
import org.sid.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportingDocRepository  extends JpaRepository<SupportingDocument,Long> {

    @Query("select d from SupportingDocument d where d.customer =:key ")
    List<SupportingDocument> findByIdUser(Long key);

}
