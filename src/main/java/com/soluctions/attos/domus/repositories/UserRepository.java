package com.soluctions.attos.domus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluctions.attos.domus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
