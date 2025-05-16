package com.soluctions.attos.domus.dtos;

import java.util.Date;

import com.soluctions.attos.domus.utils.Gender;
import com.soluctions.attos.domus.utils.MaritalStatus;

public record PersonDto(Long id, String firstName, String lastName, Date birthDate, String cpf, String rg,
                String fatherName, String motherName, MaritalStatus maritalStatus, Gender gender, String status,
                String cep, String street, String neighborhood, String state, String city, Long number,
                String complement, String email, String username, String password) {

}
