package org.sid.authenticationservice.restApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.exceptions.NotFoundException;
import org.sid.authenticationservice.models.MyUserDetails;
import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.payload.request.SignupRequest;
import org.sid.authenticationservice.security.CurrentUser;
import org.sid.authenticationservice.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ApiOperation(value = "Return authenticated user", response = User.class)
    @GetMapping("/user/me")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getCurrentUser(@ApiParam(value = "currently authenticated", required = true) @CurrentUser MyUserDetails myUserDetails) {
        return userRepository.findById(myUserDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", myUserDetails.getId()));
    }
    @ApiOperation(value = "Get all users", response = ResponseEntity.class)
    //@PreAuthorize("hasRole('DEVELOPER') or hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping
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

        return userRepository.findById(id);
    }
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "userId") Long id) {
        if(userRepository.existsById(id)) {
//            System.out.println("Server 2 *********");
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





}




