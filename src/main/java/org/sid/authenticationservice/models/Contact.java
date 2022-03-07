package org.sid.authenticationservice.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonDeserialize(as = NaturalPerson.class)
@Table(	name = "contact")
public  class Contact {
//oumaima
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User idUser;


    public Contact(String address, String city, String zipcode, String mobile, String phone) {
        this.address=address;
        this.city=city;
        this.zipcode=zipcode;
        this.mobile=mobile;
        this.phone=phone;
    }
    public Contact(String address, String city, String zipcode, String mobile, String phone, User idUser) {
        this.address=address;
        this.city=city;
        this.zipcode=zipcode;
        this.mobile=mobile;
        this.phone=phone;
        this.idUser=idUser;
    }

    public Contact(Long id) {
        this.id=id;
    }
}
