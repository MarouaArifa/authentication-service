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
@Table(	name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double salary;

    @Column
    private int seniority;
    @NotBlank
    @Size(max = 20)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContact", referencedColumnName = "id")
    private Contact idContact;

}
