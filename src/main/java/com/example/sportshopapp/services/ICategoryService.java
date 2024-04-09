package com.example.sportshopapp.services;

import com.example.sportshopapp.dtos.CategoryDTO;
import com.example.sportshopapp.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();
    Category insertCategory(CategoryDTO categoryDTO) throws Exception;
    Category updateCategory(Long categoryID, CategoryDTO categoryDTO) throws Exception;
    void deleteCategory(Long categoryID);
}
