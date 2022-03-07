package org.sid.authenticationservice.restApi;


import org.sid.authenticationservice.config.repository.AccountRepository;
import org.sid.authenticationservice.config.repository.UserRepository;
import org.sid.authenticationservice.config.repository.CustomerRepository;
import org.sid.authenticationservice.exceptions.NotFoundException;
import org.sid.authenticationservice.models.Account;
import org.sid.authenticationservice.models.Customer;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/createAcc")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest accRequest) {
                Account a = new Account( accRequest.getRib(),
                accRequest.getBalance(),
                accRequest.getOpeningDate(),
                accRequest.getCustomer());

        accRep.save(a);
        userRepository.updateAccount(accRequest.getCustomer());
        return ResponseEntity.ok(new MessageResponse("account registered successfully!"));

    }

   /* @PutMapping("/updateAccount")
    public int updateAccount(@Valid @RequestBody AccountRequest accRequest) throws NotFoundException {

       return  userRepository.updateAccount(accRequest.getCustomer());
    }
*/
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accRep.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Account> findById(@PathVariable (value = "id") Long id) {

        return accRep.findById(id);
    }

    @GetMapping("findByUser/{key}")
    public Optional<Account> findByUser(@PathVariable (value = "key") Long key) {

        return accRep.findAccountByUser(key).stream().findFirst();

    }


    @PostMapping("/updateBalance")
    public int updateBalance() {
        List<Account> l=accRep.findAll();
        for (Account a:l) {
            Customer c=customerRepository.findById(a.getCustomer()).get();
            a.setBalance(a.getBalance()+c.getAnnualIncome()/12);
            accRep.save(a);
        }

        return 1;
    }


    @PutMapping("/updateBalanceM/{id}/{m}")
    public int updateBalanceM(@PathVariable(value = "id") Long id,@PathVariable(value = "m") double m) throws NotFoundException {

        return  accRep.updateBalanceM(id,m);
    }

}
