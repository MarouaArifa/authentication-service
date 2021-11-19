package org.sid.authenticationservice.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.ECivilStatus;
import org.sid.authenticationservice.models.EGender;
import org.sid.authenticationservice.models.ERole;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class WorkerRequest  {
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
    private double salary;
    private int seniority;
    @NotBlank
    @Size(max = 20)
    private String address;

    @NotBlank
    @Size(max = 20)
    private String city;

    @Size(max =4 )
    private String zipcode;
    @NotBlank
    @Size(max = 20)
    private String mobile;
    @Size(max = 20)
    private String phone;
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




}
