package org.sid.authenticationservice.config.repository;


import org.sid.authenticationservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.customer =:id ")
    List<Account> findAccountByUser(Long id);


    @Modifying
    @Transactional
    @Query("update Account a set a.balance = a.balance - :m  where a.id =:id ")
    int updateBalanceM(Long id, double m);





}
