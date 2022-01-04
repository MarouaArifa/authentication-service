package org.sid.authenticationservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.authenticationservice.models.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public  class ContactRequest {


    @NotBlank
    @Size(max = 200)
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
    private User idUser;
}
