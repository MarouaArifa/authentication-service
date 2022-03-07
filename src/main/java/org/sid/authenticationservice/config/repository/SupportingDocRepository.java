package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface SupportingDocRepository  extends JpaRepository<SupportingDocument,Long> {

    @Query("select d from SupportingDocument d where d.customer =:key ")
    List<SupportingDocument> findByIdUser(Long key);



    @Modifying
    @Transactional
    @Query("delete from SupportingDocument s where s.customer =:id")
    int deleteDoc(Long id);
}
