package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.config.repository.WorkerRepository;
import org.sid.authenticationservice.exceptions.NotFoundException;
import org.sid.authenticationservice.models.ERole;
import org.sid.authenticationservice.models.NaturalPerson;
import org.sid.authenticationservice.models.Worker;
import org.sid.authenticationservice.payload.request.LoginRequest;
import org.sid.authenticationservice.payload.request.NaturalPersonRequest;
import org.sid.authenticationservice.payload.request.WorkerRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/addWorker")
    public ResponseEntity<?> addWorker(@Valid @RequestBody WorkerRequest workerRequest) {

        if (userRepository.existsByUsername(workerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(workerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        Worker w = new Worker(workerRequest.getUsername(),
                workerRequest.getEmail(),
                workerRequest.getRole(),
                encoder.encode(workerRequest.getPassword()),
                workerRequest.getSalary(),
                workerRequest.getSeniority()
                );
        workerRepository.save(w);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
//à tester
    @PutMapping ("/update/{id}")
    public ResponseEntity<?>  updateUer(@Valid @RequestBody WorkerRequest workerRequest , @PathVariable(value = "id" ) Long id) throws NotFoundException {
        Optional<Worker> np = Optional.ofNullable(workerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not Found for this id ::" + id)));
        Worker n = np.get();
        n.setSalary(workerRequest.getSalary());
        n.setEmail(workerRequest.getEmail());
        n.setSeniority(workerRequest.getSeniority());

        workerRepository.saveAndFlush(n) ;
        return new ResponseEntity<>("Worker is successfully updated", HttpStatus.OK);
    }
    
}
