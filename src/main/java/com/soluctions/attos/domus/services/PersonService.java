package com.soluctions.attos.domus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.soluctions.attos.domus.entities.Address;
import com.soluctions.attos.domus.entities.Role.Roles;
import com.soluctions.attos.domus.entities.User;
import com.soluctions.attos.domus.exceptions.ResourceNotFound;
import com.soluctions.attos.domus.repositories.AddressRepository;
import com.soluctions.attos.domus.repositories.RoleRepository;
import com.soluctions.attos.domus.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public List<Person> findAllPersons() {
        List<Person> personList = new ArrayList<>();

        try {
            personList = personRepository.findAll();

            return personList;

        } catch (Exception e) {
            log.error("Error in list persons: ", e);
            return null;
        }
    }

    public Person findById(Long id) {
        try {
            Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFound("person id: " + id + " not found"));

            return person;

        } catch (Exception e) {
            log.error("Error in list person by id: ", e);
            return null;
        }
    }

    public void newPerson(PersonDto personDto) {
        try {
            var personInDb = personRepository.findByCpf(personDto.cpf());
            var userRole = roleRepository.findByRoleName(Roles.USER.name());

            if (personInDb.isPresent()) {
                throw new DataIntegrityViolationException("User already exists");
            }

            var person = new Person();
            var personAddress = new Address();
            var user = new User();

            person.setFirstName(personDto.firstName());
            person.setLastName(personDto.lastName());
            person.setBirthDate(personDto.birthDate());
            person.setCpf(personDto.cpf());
            person.setRg(personDto.rg());
            person.setFatherName(personDto.fatherName());
            person.setMotherName(personDto.motherName());
            person.setMaritalStatus(personDto.maritalStatus());
            person.setGender(personDto.gender());
            if(personDto.status() != null && !personDto.status().isEmpty()){
                person.setStatus(personDto.status());
            }else{
                person.setStatus("Ativo");
            }


            personAddress.setCep(personDto.cep());
            personAddress.setStreet(personDto.street());
            personAddress.setNeighborhood(personDto.neighborhood());
            personAddress.setState(personDto.state());
            personAddress.setCity(personDto.city());
            personAddress.setNumber(personDto.number());
            personAddress.setComplement(personDto.complement());
            personAddress.setPersonId(person);

            user.setEmail(personDto.email());
            user.setUsername(personDto.username());
            user.setPassword(passwordEncoder.encode(personDto.password()));
            user.setRoles(Set.of(userRole));
            user.setPerson(person);

            personRepository.save(person);
            addressRepository.save(personAddress);
            userRepository.save(user);

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
