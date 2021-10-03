package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.config.repository.CustomerRepository;
import org.sid.authenticationservice.config.repository.NaturalPersonRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.models.*;
import org.sid.authenticationservice.payload.request.ContactRequest;
import org.sid.authenticationservice.payload.request.NaturalPersonRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/contact")
public class NaturalPersonController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NaturalPersonRepository npRepository;
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@Valid @RequestBody NaturalPersonRequest npRequest) {

        // Create new user's account
        NaturalPerson np = new NaturalPerson(npRequest.getAddress(),npRequest.getCity(), npRequest.getZipcode(),npRequest.getMobile(),
                npRequest.getPhone(),npRequest.getIdUser(),npRequest.getCin(), npRequest.getFirstname(),npRequest.getLastname(), npRequest.getBirthday(),
                npRequest.getGender(), npRequest.getCivilStatus(), npRequest.getChildNumber());
        NaturalPerson n=npRepository.save(np);
        User u= userRepository.findByid(np.getIdUser().getId());
        System.out.println(" rrrrrrrrrrrrrrrrrrrrrrr"+n.getId()+"eeeeeee"+ u.getRole());
      if(u.getRole()== ERole.ROLE_CLIENT)
        {
            System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");

            Customer c =new Customer(new Contact(n.getId()));
            customerRepository.save(c);
       }

        return ResponseEntity.ok(new MessageResponse("contact registered successfully!"));
    }
}

