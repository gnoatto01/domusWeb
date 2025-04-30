package com.soluctions.attos.domus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soluctions.attos.domus.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCpf(String cpf);

    @Query(value = " update persons p set status = 'Inativo' where p.id = :id ", nativeQuery = true)
    void inativePerson(@Param("id") Long id);
}
