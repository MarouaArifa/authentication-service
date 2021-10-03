package org.sid.authenticationservice.config.repository;

import org.sid.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
