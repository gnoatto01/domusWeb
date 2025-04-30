package com.soluctions.attos.domus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.soluctions.attos.domus.dtos.PersonDto;
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

    public Optional<Person> listById(Long id) {
        try {
            var person = personRepository.findById(id);

            return person;

        } catch (Exception e) {
            log.error("Error in list person by id: ", e);
            return null;
        }
    }

    public void newPerson(PersonDto personDto) {
        try {
            var personInDb = personRepository.findByCpf(personDto.cpf());

            if (personInDb.isPresent()) {
                throw new DataIntegrityViolationException("User already exists");
            }

            var person = new Person();

            person.setFirstName(personDto.firstName());
            person.setLastName(personDto.lastName());
            person.setBirthDate(personDto.birthDate());
            person.setCpf(personDto.cpf());
            person.setRg(personDto.rg());
            person.setFatherName(personDto.fatherName());
            person.setMotherName(personDto.motherName());
            person.setMaritalStatus(personDto.maritalStatus());
            person.setGender(personDto.gender());
            person.setStatus(personDto.status());

            personRepository.save(person);

        } catch (Exception e) {
            log.error("Error in register new person: ", e);
        }
    }

    public void inativePerson(Long id) {
        try {
            personRepository.inativePerson(id);

        } catch (Exception e) {
            log.error("Error in inative person: ", e);
        }
    }

}
