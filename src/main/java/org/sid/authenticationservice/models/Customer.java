package org.sid.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idContact", referencedColumnName = "id")
    private Contact idContact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEcoInfo", referencedColumnName = "id")
    private EconomicInformation idEcoInfo;
    @OneToMany (mappedBy="customer")
    private Set<SupportingDocument> supportingDocuments;

    @OneToMany (mappedBy="customer")
    private Set<Account> accounts;

    public Customer(Contact idContact) {
        this.idContact = idContact;
    }
}
