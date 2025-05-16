package com.soluctions.attos.domus.repositories;

import com.soluctions.attos.domus.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
