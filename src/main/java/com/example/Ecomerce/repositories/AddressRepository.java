package com.example.Ecomerce.repositories;

import com.example.Ecomerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
