package org.sid.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends  User{

    @NotBlank
    @Size(max = 20)
    private String empTitle;
    private int empLength;
    @NotBlank
    @Size(max = 100)
    private String workEstablishement;
    @NotBlank
    @Size(max = 100)
    private String workAddress;
    private boolean hasMatriculeFiscal;
    @Size(max = 100)
    private String matriculeFiscal;

    @OneToMany (mappedBy="customer")
    private Set<SupportingDocument> supportingDocuments;

    @OneToMany (mappedBy="customer")
    private Set<Account> accounts;

    public Customer(String username, String email, ERole roleClient, String encode, String empTitle,
                    int empLength, String workEstablishement, String workAddress,
                    boolean hasMatriculeFiscal, String matriculeFiscal)
    {
        super(username,email, roleClient, encode );
        this.empTitle=empTitle;
        this.empLength=empLength;
        this.workEstablishement=workEstablishement;
        this.workAddress=workAddress;
        this.hasMatriculeFiscal=hasMatriculeFiscal;
        this.matriculeFiscal=matriculeFiscal;

    }

    public Customer(Contact idContact) {
        super(idContact);

    }
}
