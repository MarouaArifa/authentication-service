package org.sid.authenticationservice.restApi;

import org.sid.authenticationservice.config.repository.SupportingDocRepository;
import org.sid.authenticationservice.models.SupportingDocument;
import org.sid.authenticationservice.payload.request.SupportingDocRequest;
import org.sid.authenticationservice.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/supportingDoc")
public class SupportingDocController {

    @Autowired
    SupportingDocRepository docRepository;

    @PostMapping("/addDocLoan")
    public ResponseEntity<?> addDocLoan(@Valid @RequestBody SupportingDocRequest docRequest) {
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        SupportingDocument d = new SupportingDocument(
                docRequest.getType(),
                docRequest.getPath(),
                docRequest.getCustomer());
        docRepository.save(d);
        return ResponseEntity.ok(new MessageResponse("Documents registered successfully!"));

    }


}
