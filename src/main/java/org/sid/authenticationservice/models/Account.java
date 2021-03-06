package org.sid.authenticationservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @NotBlank
    @Size(max = 20)
    private String rib;
    private double balance;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date openingDate;

    private Long customer;


    public Account(String rib, double balance, Date openingDate, Long customer) {
        this.rib = rib;
        this.balance = balance;
        this.openingDate = openingDate;
        this.customer = customer;
    }
}
