package org.sid.authenticationservice.config.repository;


import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.customer =:id ")
    List<Account> findAccountByUser(Long id);

}
