package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.User;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<User> findById(Long id);
    User findByid(Long id);
    Boolean existsById(String id);
    Boolean existsByEmail(String email);

    @Query("select u from User u where u.verif = 0 and dtype like '%Customer%' and u.role like '%ROLE_CLIENT%'")
    List<User> allReq();

    @Query("select u from User u where u.verif = 1 and dtype like '%Customer%' ")
    List<User> all();

    @Query("select u from User u where  dtype like '%Worker%' ")
    List<User> employees();

    @Query("select u from User u where u.username =:key ")
    List<User> findByUserName(String key);

    @Query("select u from User u where u.username =:key ")
    List<User> findByUserNameV2(String key);


    @Modifying
    @Transactional
    @Query("update User u set u.verif = true where u.id =:id ")
    int updateVerif(Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.verif = false where u.id =:id ")
    int updateVerifRefuse(Long id);


    @Modifying
    @Transactional
    @Query("update User u set u.password = :pwd where u.id =:id ")
    int updatePassword(Long id,String pwd);


    @Modifying
    @Transactional
    @Query("update User u set u.accounts = (select a.id from Account a where a.customer =:id)  where u.id =:id")
    int updateAccount(Long id);





}
