package org.sid.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class NaturalPerson extends Contact{

    @Column(unique=true)
    @NotBlank
    @Size(max = 8)
    private String cin;
    @NotBlank
    @Size(max = 20)
    private String firstname;
    @NotBlank
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

    public NaturalPerson(String address, String city, String zipcode, String mobile, String phone,User u, String cin, String firstname, String lastname, Date birthday, EGender gender, ECivilStatus civilStatus, int childNumber)
    {
        super(address, city, zipcode, mobile, phone,u);
        this.cin=cin;
        this.firstname=firstname;
        this.lastname=lastname;
        this.birthday=birthday;
        this.gender=gender;
        this.childNumber=childNumber;
        this.civilStatus=civilStatus;
    }
}
