package com.example.sportshopapp.repositories;

import com.example.sportshopapp.models.Cart;
import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.models.Size;
import com.example.sportshopapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> getCartByUserAndProductAndSize(User user, Product product, Size size);
    Optional<Cart> getCartByUserAndProduct(User user, Product product);
    List<Cart> getCartsByUser(User user);
    void deleteCartByUser(User user);
    void deleteCartsByUserAndProductAndSize(User user, Product product, Size size);
    void deleteCartsByUserAndProduct(User user, Product product);
    Boolean existsByUserAndProduct(User user, Product product);
    Boolean existsByUserAndProductAndSize(User user, Product product, Size size);
}
