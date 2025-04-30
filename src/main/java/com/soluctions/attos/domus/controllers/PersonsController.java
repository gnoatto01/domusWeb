package com.soluctions.attos.domus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soluctions.attos.domus.dtos.PersonDto;
import com.soluctions.attos.domus.entities.Person;
import com.soluctions.attos.domus.services.PersonService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/attos-api")
@RestController
@AllArgsConstructor
@Slf4j
public class PersonsController {
    private final PersonService personService;

    @GetMapping("/list-persons")
    public ResponseEntity<List<Person>> listAllPersons() {

        List<Person> personsList = new ArrayList<>();

        try {
            personsList = personService.listAllPersons();

            return ResponseEntity.ok(personsList);

        } catch (Exception e) {
            log.error("Error controller, list persons: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list-person/{id}")
    public ResponseEntity<Person> listPersonById(@PathVariable("id") Long id) {
        try {
            var person = personService.listById(id);

            return ResponseEntity.ok(person.get());

        } catch (Exception e) {
            log.error("Error in controller, list person by id: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new-person")
    public ResponseEntity<Void> newPerson(@RequestBody PersonDto personDto) {
        try {
            personService.newPerson(personDto);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error in new person controller: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/inative-person/{id}")
    public ResponseEntity<Void> inativePerson(@PathVariable("id") Long id) {
        try {
            personService.inativePerson(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error in inative person, controller: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
