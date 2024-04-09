package com.example.sportshopapp.repositories;

import com.example.sportshopapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
