package org.sid.authenticationservice.config.repository;


import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WorkerRepository  extends JpaRepository<Worker, Long> {
}
