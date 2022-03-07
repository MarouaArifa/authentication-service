package org.sid.authenticationservice.restApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.sid.authenticationservice.config.repository.ContactRepository;
import org.sid.authenticationservice.config.repository.SupportingDocRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.exceptions.NotFoundException;
import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.models.MyUserDetails;
import org.sid.authenticationservice.models.NaturalPerson;
import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.payload.request.ContactRequest;
import org.sid.authenticationservice.payload.request.SignupRequest;
import org.sid.authenticationservice.security.CurrentUser;
import org.sid.authenticationservice.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SupportingDocRepository docRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Return authenticated user", response = User.class)
    @GetMapping("/user/me")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getCurrentUser(@ApiParam(value = "currently authenticated", required = true) @CurrentUser MyUserDetails myUserDetails) {
        return userRepository.findById(myUserDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", myUserDetails.getId()));
    }
    @ApiOperation(value = "Get all users", response = ResponseEntity.class)
    //@PreAuthorize("hasRole('DEVELOPER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/list")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/object")
    public List<User> findAllObjects() {

        return userRepository.findAll();
    }
    @ApiOperation(value = "Get users By id", response = ResponseEntity.class)

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable (value = "id") Long id) {
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        return userRepository.findById(id);
    }
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "userId") Long id) {
        if(userRepository.existsById(id)) {
             return userRepository.findByid(id);

        } else {
            throw new ResourceNotFoundException("Task not found","id",id);
        }
        }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable (value = "id") Long id,@RequestBody SignupRequest signupRequest) throws NotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("user not Found for this id ::"+id));
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword());
        userRepository.save(user);
            return new ResponseEntity<>(" status of training successfully updated",HttpStatus.OK);
        }




    @ApiOperation(value = "Get all requests", response = ResponseEntity.class)
    @GetMapping("/allReq")
    public List<User> getAllReq() {
        return userRepository.allReq();
    }


    @ApiOperation(value = "Get all requests", response = ResponseEntity.class)
    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.all();
    }



    @ApiOperation(value = "Get all employees", response = ResponseEntity.class)
    @GetMapping("/allEmployees")
    public List<User> getAllEmployees() {
        return userRepository.employees();
    }





    @PutMapping("/updateV/{id}")
    public int updateVerif(@PathVariable(value = "id") Long id) throws NotFoundException {
        Optional<User> us = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not Found for this id ::" + id)));
           return  userRepository.updateVerif(id);
        //return new ResponseEntity<>("User is successfully updated", HttpStatus.OK);
    }


    @PutMapping("/updateVRef/{id}")
    public int updateVerifRef(@PathVariable(value = "id") Long id) throws NotFoundException {
        Optional<User> us = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not Found for this id ::" + id)));
        return  userRepository.updateVerifRefuse(id);

    }





    //some other code

  /* @PostMapping(value = "/email")
    public ResponseEntity<User> enviarEmail( @RequestBody User user){
        try {
            emailService.sendEmail(user);
            return new ResponseEntity<>(user,  HttpStatus.OK);
        } catch( Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
*/




    @PostMapping(value = "/emailAccount")
    public ResponseEntity<User> enviarEmailAccount(@RequestBody User user){
        try {
            emailService.sendEmailAccount(user);
            return new ResponseEntity<>(user,  HttpStatus.OK);
        } catch( Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @ApiOperation(value = "delete user by Id", response = ResponseEntity.class)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {

        Optional<User> u = userRepository.findById(userId);
        //contactRepository.deleteContact(userId);
        //docRepository.deleteDoc(userId);
        if (u.isPresent()) {
            userRepository.delete(u.get());
            return new ResponseEntity("doc successfully deleted", HttpStatus.OK);

        } else {
            throw new NotFoundException("user not found");
        }
    }




    @PutMapping("/updatePassword/{id}/{pwd}")
    public int updatePassword(@PathVariable(value = "id") Long id, @PathVariable(value = "pwd") String pwd) throws NotFoundException {

        return  userRepository.updatePassword(id, passwordEncoder.encode(pwd));
        //return new ResponseEntity<>("User is successfully updated", HttpStatus.OK);
    }



}




