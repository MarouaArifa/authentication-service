package org.sid.authenticationservice.restApi;


import org.sid.authenticationservice.config.repository.AccountRepository;
import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.payload.request.AccountRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountRepository accRep;



    @PostMapping("/createAcc")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest accRequest) {
                Account a = new Account( accRequest.getRib(),
                accRequest.getBalance(),
                accRequest.getOpeningDate(),
                accRequest.getCustomer());

        accRep.save(a);
        return ResponseEntity.ok(new MessageResponse("account registered successfully!"));

    }

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accRep.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Account> findById(@PathVariable (value = "id") Long id) {

        return accRep.findById(id);
    }





}
