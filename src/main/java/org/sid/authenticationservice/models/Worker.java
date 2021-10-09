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

public class Worker extends User {
    @Column
    private double salary;
    @Column
    private int seniority;


    public Worker(String username, String email, ERole role, String encode, double salary, int seniority) {

        super(username,email, role, encode );
        this.salary=salary;
        this.seniority=seniority;
    }
}
