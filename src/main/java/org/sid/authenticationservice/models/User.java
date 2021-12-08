package org.sid.authenticationservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max = 20)
    private String username;



    @Size(max = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;



    @Size(max = 120)
    private String email;

    private boolean verif;



    public User(String username, String email, String encode) {
        this.username=username;
        this.email=email;
        this.password=encode;

    }

    public User(String username, String email, ERole role, String encode) {
        this.username=username;
        this.email=email;
        this.role=role;
        this.password=encode;
    }
    public User(Long id,String username) {
        this.id=id;
        this.username=username;

    }

    public User(Long id, String username, String password, ERole role, String email, boolean verif) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.verif = verif;
    }
}

