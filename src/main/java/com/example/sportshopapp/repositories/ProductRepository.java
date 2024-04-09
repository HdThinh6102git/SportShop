package com.example.sportshopapp.repositories;

import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId IS null or :categoryId = 0 or p.category.id = :categoryId or p.category.parentCategory.id = :categoryId)" +
            "AND (:keyword is null or :keyword = '' or p.name like %:keyword%)")
    Page<Product> getProducts
            (@Param("keyword") String keyword,
             @Param("categoryId") Long categoryId,
             PageRequest pageRequest);
}
