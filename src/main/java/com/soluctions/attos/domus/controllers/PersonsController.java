package com.soluctions.attos.domus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
