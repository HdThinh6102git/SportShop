package com.example.sportshopapp.repositories;

import com.example.sportshopapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> getCategoriesByParentCategoryIsNull();
    Optional<Category> findByName(String name);

    List<Category> findCategoryByParentCategoryIsNull();
}
