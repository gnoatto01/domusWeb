package com.soluctions.attos.domus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soluctions.attos.domus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = " update users u set status = 'Inactive' where u.id = :id ", nativeQuery = true)
    void inactiveUser(@Param("id") Long id);

    @Query(value = " select count(*) from users u where u.email = :email ", nativeQuery = true)
    Integer findUserByEmail(String email); 
}
