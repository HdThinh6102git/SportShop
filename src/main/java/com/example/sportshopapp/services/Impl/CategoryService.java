package com.example.sportshopapp.services.Impl;

import com.example.sportshopapp.common.MessageKeys;
import com.example.sportshopapp.dtos.CategoryDTO;
import com.example.sportshopapp.models.Category;
import com.example.sportshopapp.repositories.CategoryRepository;
import com.example.sportshopapp.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategoriesByParentCategoryIsNull();
    }

    @Override
    @Transactional
    public Category insertCategory(CategoryDTO categoryDTO) throws Exception {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryDTO.getName());
        if (existingCategory.isPresent()){
           throw new Exception(MessageKeys.ALREADY_CATEGORY_NAME);
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        if (categoryDTO.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new Exception(MessageKeys.PARENT_CATEGORY_NOT_EXISTING));
            category.setParentCategory(parentCategory);
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Long categoryID, CategoryDTO categoryDTO) throws Exception {
        Optional<Category> existingCategory = categoryRepository.findById(categoryID);
        if (existingCategory.isEmpty()){
            throw new Exception(MessageKeys.CATEGORY_NOT_EXISTING);
        }
        if (categoryDTO.getParentId() != null){
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new Exception(MessageKeys.PARENT_CATEGORY_NOT_EXISTING));
            existingCategory.get().setParentCategory(parentCategory);
        }
        return categoryRepository.save(existingCategory.get());
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryID) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryID);
        if(existingCategory.isPresent()){
            categoryRepository.deleteById(categoryID);
        }
    }
}
