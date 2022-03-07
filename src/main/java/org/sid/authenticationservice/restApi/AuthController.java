package org.sid.authenticationservice.restApi;
import org.sid.authenticationservice.config.repository.NaturalPersonRepository;
import org.sid.authenticationservice.config.repository.SupportingDocRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.models.*;
import org.sid.authenticationservice.payload.request.LoginRequest;
import org.sid.authenticationservice.payload.request.NaturalPersonRequest;
import org.sid.authenticationservice.payload.request.SignupRequest;
import org.sid.authenticationservice.payload.request.SupportingDocRequest;
import org.sid.authenticationservice.payload.response.JwtResponse;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.sid.authenticationservice.security.JwtUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private EmailService emailService;

    @Autowired
    SupportingDocRepository docRepository;


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
                signUpRequest.getId(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                ERole.ROLE_CLIENT,
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmpTitle(),
                signUpRequest.getEmpLength(),
                signUpRequest.getAnnualIncome(),
                signUpRequest.getWorkEstablishement(),
                signUpRequest.getWorkAddress(),
                signUpRequest.isHasMatriculeFiscal(),
                signUpRequest.getMatriculeFiscal(),
                signUpRequest.getVerif()

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


    @GetMapping("findByUserNameV2/{key}")
    public List<User> findByUserName(@PathVariable (value = "key") String key) {

        return userRepository.findByUserNameV2(key);

    }




    @PostMapping("/addDoc")
    public ResponseEntity<?> addDocLoan(@RequestBody SupportingDocRequest docRequest) {
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"+docRequest.getType());
        String[] tab = docRequest.getType().split(Pattern.quote("|"));
        System.out.println("taaaaaaaaaaaaaaaaaaaaaaaab 0 "+tab[0]);
        System.out.println("taaaaaaaaaaaaaaaaaaaaaaab 1  "+tab[1]);
        docRequest.setType(tab[0]);
        docRequest.setCustomer(Long.valueOf( tab[1]));


        SupportingDocument d = new SupportingDocument(
                docRequest.getType(),
                docRequest.getPath(),
                docRequest.getCustomer());

        docRepository.save(d);

        return ResponseEntity.ok(new MessageResponse("Documents registered successfully!"));

    }




    @PostMapping(value = "/email")
    public ResponseEntity<User> enviarEmail( @RequestBody User user){
        try {
            emailService.sendEmail(user);
            return new ResponseEntity<>(user,  HttpStatus.OK);
        } catch( Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PostMapping(value = "/emailRefuse")
    public ResponseEntity<User> enviarEmailRefuse( @RequestBody User user){
        try {
            emailService.sendEmailRefuse(user);
            return new ResponseEntity<>(user,  HttpStatus.OK);
        } catch( Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
