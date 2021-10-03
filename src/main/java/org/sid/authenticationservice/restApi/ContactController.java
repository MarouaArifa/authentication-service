package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.config.repository.ContactRepository;
import org.sid.authenticationservice.config.repository.NaturalPersonRepository;
import org.sid.authenticationservice.models.Contact;
import org.sid.authenticationservice.models.ERole;
import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.payload.request.ContactRequest;
import org.sid.authenticationservice.payload.request.SignupRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;


}
