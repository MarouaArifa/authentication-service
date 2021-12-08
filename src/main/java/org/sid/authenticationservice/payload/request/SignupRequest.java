package org.sid.authenticationservice.payload.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.ECivilStatus;
import org.sid.authenticationservice.models.EGender;
import org.sid.authenticationservice.models.ERole;
import org.sid.authenticationservice.models.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

     private Long id;



    @Size(min = 3, max = 20)
    private String username;


    @Size(max = 50)
    @Email
    private String email;

    private ERole role;


    @Size(min = 6, max = 40)
    private String password;


    @Size(max = 100)
    private String empTitle;

    private int empLength;

    private String workEstablishement;


    @Size(max = 100)
    private String workAddress;

    private boolean hasMatriculeFiscal;

    @Size(max = 100)
    private String matriculeFiscal;


    @Size(max = 20)
    private String address;


    @Size(max = 20)
    private String city;

    @Size(max =4 )
    private String zipcode;

    @Size(max = 20)
    private String mobile;
    @Size(max = 20)
    private String phone;

    @Size(max = 8)
    private String cin;

    @Size(max = 20)
    private String firstname;

    @Size(max = 20)
    private String lastname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ECivilStatus civilStatus;
    private int childNumber;
    private boolean verif;

    public boolean getVerif() {
        return verif;
    }

    public void setVerif(boolean verif) {
        this.verif = verif;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
