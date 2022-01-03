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


    @Size(max = 20)
    private String empTitle;
    private int empLength;
    @Column
    private double annualIncome;
    @Size(max = 100)
    private String workEstablishement;

    @Size(max = 100)
    private String workAddress;
    private boolean hasMatriculeFiscal;
    @Size(max = 100)
    private String matriculeFiscal;
    private boolean verif;

   @OneToMany (mappedBy="customer")
    private Set<SupportingDocument> supportingDocuments;

    @OneToMany (mappedBy="customer")
    private Set<Account> accounts;

    public Customer(Long id,String username, String email, ERole roleClient, String encode, String empTitle,
                    int empLength, Double annualIncome,String workEstablishement, String workAddress,
                    boolean hasMatriculeFiscal, String matriculeFiscal,boolean verif)
    {
        super(id, username, encode, roleClient,  email,  verif );
        this.empTitle=empTitle;
        this.empLength=empLength;
        this.annualIncome=annualIncome;
        this.workEstablishement=workEstablishement;
        this.workAddress=workAddress;
        this.hasMatriculeFiscal=hasMatriculeFiscal;
        this.matriculeFiscal=matriculeFiscal;
        this.verif=verif;

    }


}
