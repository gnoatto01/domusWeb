package com.soluctions.attos.domus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluctions.attos.domus.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
