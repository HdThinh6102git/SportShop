package com.example.sportshopapp.controllers;

import com.example.sportshopapp.common.ValidationErrorHandler;
import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.ProductDetailResponse;
import com.example.sportshopapp.response.ProductListResponse;
import com.example.sportshopapp.services.Impl.CategoryService;
import com.example.sportshopapp.services.Impl.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ValidationErrorHandler validationErrorHandler;
    @GetMapping("")
    private ResponseEntity<BaseResponse<ProductListResponse>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(name = "category_id", defaultValue = "0") Long categoryID,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int limit
    ){
        PageRequest pageRequest = PageRequest.of(
                page,limit, Sort.by("id").ascending()
        );
        BaseResponse<ProductListResponse> response = productService.getProducts(keyword,categoryID,pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{product_id}")
    private ResponseEntity<BaseResponse<ProductDetailResponse>> getDetailProduct(
            @PathVariable("product_id") Long productId
    ){
       BaseResponse<ProductDetailResponse> response = productService.getDetailProduct(productId);
       return ResponseEntity.ok(response);
    }

    @PostMapping("")
    private ResponseEntity<BaseResponse<Void>> insertProduct(
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
    ){
        if (result.hasErrors()){
           BaseResponse<Void> errorResponse = validationErrorHandler.handleValidationErrors(result);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        BaseResponse<Void> response = productService.insertProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{product_id}")
    private ResponseEntity<BaseResponse<Void>> updateProduct(
            @PathVariable("product_id") Long productId,
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
    ) {
        if (result.hasErrors()){
            BaseResponse<Void> errorResponse = validationErrorHandler.handleValidationErrors(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        BaseResponse<Void> response = productService.updateProduct(productDTO,productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{product_id}")
    private ResponseEntity<BaseResponse<Void>> deleteProduct(
            @PathVariable("product_id") Long productID
    ){
        BaseResponse<Void> response = productService.deleteProduct(productID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
