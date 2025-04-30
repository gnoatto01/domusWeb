package com.soluctions.attos.domus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soluctions.attos.domus.entities.Person;
import com.soluctions.attos.domus.repositories.PersonRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> listAllPersons() {
        List<Person> personList = new ArrayList<>();

        try {
            personList = personRepository.findAll();

            return personList;

        } catch (Exception e) {
            log.error("Error in list persons: ", e);
            return null;
        }
    }

}
