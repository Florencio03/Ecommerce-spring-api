package com.example.Ecomerce.repositories;

import com.example.Ecomerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
