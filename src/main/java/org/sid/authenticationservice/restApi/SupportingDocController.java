package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.config.repository.SupportingDocRepository;
import org.sid.authenticationservice.models.SupportingDocument;
import org.sid.authenticationservice.models.User;
import org.sid.authenticationservice.payload.request.SupportingDocRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/supportingDoc")
public class SupportingDocController {

    @Autowired
    SupportingDocRepository docRepository;

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

    @GetMapping("findByIdUser/{key}")
    public List<SupportingDocument> findByIdUser(@PathVariable (value = "key") Long key) {

        return docRepository.findByIdUser(key);

    }

    @GetMapping("/findById/{id}")
    public Optional<SupportingDocument> findById(@PathVariable (value = "id") Long id) {

        return docRepository.findById(id);

    }

}
