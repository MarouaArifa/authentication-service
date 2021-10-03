package org.sid.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "economicInformation")
public class EconomicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String empTitle;

    @NotBlank
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

}
