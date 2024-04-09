package com.example.sportshopapp.controllers;

import com.example.sportshopapp.common.MessageKeys;
import com.example.sportshopapp.dtos.CategoryDTO;
import com.example.sportshopapp.models.Category;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.services.Impl.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        try {
            List<Category> categories = categoryService.getCategories(); // Gọi service để lấy danh sách categories
            BaseResponse<List<Category>> response = BaseResponse.<List<Category>>builder()
                    .success(true)
                    .message(MessageKeys.SUCCESS)
                    .data(categories)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    BaseResponse.builder()
                            .success(false)
                            .message(MessageKeys.FAILED)
                            .build()
            );
        }
    }
    @PostMapping("")
    public ResponseEntity<?> insertCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result){
        try{
            if (result.hasErrors()){
                List<String> errorMessages = result.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(
                        BaseResponse.builder()
                                .success(false)
                                .message(errorMessages.toString())
                                .build()
                );
            }
            Category category = categoryService.insertCategory(categoryDTO);
            return ResponseEntity.ok(BaseResponse.<Category>builder()
                            .message(MessageKeys.SUCCESS)
                            .success(true)
                            .data(category)
                            .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    BaseResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
    @PutMapping("{category_id}")
    private ResponseEntity<?> updateCategory(
            @PathVariable("category_id") Long categoryId,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ){
        try {
            if (result.hasErrors()){
                List<String> errorMessages = result.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(
                        BaseResponse.builder()
                                .success(false)
                                .message(errorMessages.toString())
                                .build()
                );
            }
            Category category = categoryService.updateCategory(categoryId,categoryDTO);
            return ResponseEntity.ok(BaseResponse.<Category>builder()
                    .message(MessageKeys.SUCCESS)
                    .success(true)
                    .data(category)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    BaseResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
    @DeleteMapping("{category_id}")
    private ResponseEntity<?> deleteCategory(
            @Valid @PathVariable("category_id") @Min(value = 1, message = MessageKeys.VALIDATE_ID) Long categoryId,
            BindingResult result
    ){
        try {
            if (result.hasErrors()){
                List<String> errorMessages = result.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(
                        BaseResponse.builder()
                                .success(false)
                                .message(errorMessages.toString())
                                .build()
                );
            }
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(BaseResponse.<Category>builder()
                    .message(MessageKeys.SUCCESS)
                    .success(true)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    BaseResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
