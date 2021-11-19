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
@Table(	name = "supportingDocument")
public class SupportingDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String type;
    @NotBlank
    private String path;
    @ManyToOne
    private Customer customer;

    public SupportingDocument(String type, String path, Customer customer) {
        this.type = type;
        this.path = path;
        this.customer=customer;
    }
}
