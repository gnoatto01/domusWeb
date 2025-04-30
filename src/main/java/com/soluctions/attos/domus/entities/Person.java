package com.soluctions.attos.domus.entities;

import java.util.Date;

import com.soluctions.attos.domus.utils.Gender;
import com.soluctions.attos.domus.utils.MaritalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    @Column(unique = true)
    private String cpf;

    private String rg;

    private String fatherName;

    private String motherName;

    private MaritalStatus maritalStatus;

    private Gender gender;

}
