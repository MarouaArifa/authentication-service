package org.sid.authenticationservice.payload.request;

import lombok.Data;
import org.sid.authenticationservice.models.ERole;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CustomerRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private ERole role;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

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

}
