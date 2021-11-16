package org.sid.authenticationservice.restApi;
import org.sid.authenticationservice.config.repository.NaturalPersonRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.models.*;
import org.sid.authenticationservice.payload.request.LoginRequest;
import org.sid.authenticationservice.payload.request.NaturalPersonRequest;
import org.sid.authenticationservice.payload.request.SignupRequest;
import org.sid.authenticationservice.payload.response.JwtResponse;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.sid.authenticationservice.security.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    NaturalPersonRepository npRepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest ) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Customer c = new Customer(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                ERole.ROLE_CLIENT,
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmpTitle(),
                signUpRequest.getEmpLength(),
                signUpRequest.getWorkEstablishement(),
                signUpRequest.getWorkAddress(),
                signUpRequest.isHasMatriculeFiscal(),
                signUpRequest.getMatriculeFiscal()

        );

        userRepository.save(c);
        NaturalPerson np = new NaturalPerson(signUpRequest.getAddress(),signUpRequest.getCity(),
                signUpRequest.getZipcode(),signUpRequest.getMobile(),
                signUpRequest.getPhone(),userRepository.findByUsername(signUpRequest.getUsername()).get(),
                signUpRequest.getCin(), signUpRequest.getFirstname(),
                signUpRequest.getLastname(), signUpRequest.getBirthday(),
                signUpRequest.getGender(), signUpRequest.getCivilStatus(),
                signUpRequest.getChildNumber());

        npRepository.save(np);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
/*
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                ERole.ROLE_CLIENT,
                encoder.encode(signUpRequest.getPassword())
        );

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
*/


}
