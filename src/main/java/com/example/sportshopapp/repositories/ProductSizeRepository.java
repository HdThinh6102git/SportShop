package com.example.sportshopapp.repositories;

import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.models.ProductSize;
import com.example.sportshopapp.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long>{
    Optional<ProductSize> findProductSizeByProductAndSize(Product product, Size size);
    boolean existsProductSizeByProduct(Product product);
}
