package org.sid.authenticationservice.restApi;

import io.swagger.annotations.ApiOperation;
import org.sid.authenticationservice.config.repository.CustomerRepository;
import org.sid.authenticationservice.config.repository.NaturalPersonRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.exceptions.NotFoundException;
import org.sid.authenticationservice.models.*;
import org.sid.authenticationservice.payload.request.ContactRequest;
import org.sid.authenticationservice.payload.request.NaturalPersonRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/contact")
public class NaturalPersonController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NaturalPersonRepository npRepository;

    @PostMapping("/addContact")//
    public ResponseEntity<?> addContact(@Valid @RequestBody NaturalPersonRequest npRequest) {
// ajout id contact dans table user +ajout d'une fonction update de user pour modifier id Contact
        // Create new user's account
        NaturalPerson np = new NaturalPerson(npRequest.getAddress(),npRequest.getCity(),
                npRequest.getZipcode(),npRequest.getMobile(),
                npRequest.getPhone(),npRequest.getIdUser(),
                npRequest.getCin(), npRequest.getFirstname(),
                npRequest.getLastname(), npRequest.getBirthday(),
                npRequest.getGender(), npRequest.getCivilStatus(),
                npRequest.getChildNumber());
        NaturalPerson n=npRepository.save(np);
        User u= userRepository.findByid(np.getIdUser().getId());
        System.out.println(" rrrrrrrrrrrrrrrrrrrrrrr"+n.getId()+"eeeeeee"+ u.getRole());

        return ResponseEntity.ok(new MessageResponse("contact registered successfully!"));
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?>  updateUer(@Valid @RequestBody NaturalPersonRequest npRequest ,@PathVariable(value = "id" ) Long id) throws NotFoundException{
        Optional<NaturalPerson> np = Optional.ofNullable(npRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not Found for this id ::" + id)));
        NaturalPerson n = np.get();
        n.setBirthday(npRequest.getBirthday());
        n.setFirstname(npRequest.getFirstname());
        n.setCivilStatus(np.get().getCivilStatus());
        n.setGender(npRequest.getGender());
        n.setCity(npRequest.getCity());
        n.setAddress(npRequest.getAddress());
        n.setChildNumber(npRequest.getChildNumber());
        npRepository.saveAndFlush(n) ;
        return new ResponseEntity<>("Person is successfully updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Get Contact by id", response = ResponseEntity.class)
    @GetMapping("/{npId}")
    public Optional<NaturalPerson>  getContact(@PathVariable(value = "npId") Long id) {
        return Optional.ofNullable(npRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not Found for this id ::" + id)));
    }

}




