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
@AllArgsConstructor
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;


    @NotBlank
    @Size(max = 120)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;


    @NotBlank
    @Size(max = 120)
    private String email;

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
}

