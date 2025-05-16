package com.soluctions.attos.domus.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String street;

    private String neighborhood;

    private String state;

    private String city;

    private Long number;

    private String complement;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person personId;
}
