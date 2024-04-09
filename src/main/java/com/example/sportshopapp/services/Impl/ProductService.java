package com.example.sportshopapp.services.Impl;

import com.example.sportshopapp.common.MessageKeys;
import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.exceptions.ConstraintViolationException;
import com.example.sportshopapp.exceptions.NotFoundException;
import com.example.sportshopapp.models.Category;
import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.repositories.CategoryRepository;
import com.example.sportshopapp.repositories.ProductRepository;
import com.example.sportshopapp.repositories.ProductSizeRepository;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.ProductDetailResponse;
import com.example.sportshopapp.response.ProductListResponse;
import com.example.sportshopapp.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public BaseResponse<ProductListResponse> getProducts(String keyword, Long categoryId, PageRequest pageRequest) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(MessageKeys.CATEGORY_NOT_FOUND));
        Page<Product> productPage = productRepository.getProducts(keyword, categoryId, pageRequest);
        int totalPage = productPage.getTotalPages();
        List<ProductDTO> productDTOS = productPage.getContent().stream().map(ProductDTO::convertToProductDTO).toList();
        ProductListResponse productListResponse = ProductListResponse.builder()
                .totalPages(totalPage)
                .productList(productDTOS)
                .build();
        return BaseResponse.<ProductListResponse>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message(MessageKeys.GET_SUCCESS)
                .data(productListResponse)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<ProductDetailResponse> getDetailProduct(Long productId){
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_NOT_FOUND));
       ProductDetailResponse productDetailResponse = ProductDetailResponse.convertToProductDetailResponse(existingProduct);
       return BaseResponse.<ProductDetailResponse>builder()
               .success(true)
               .status(HttpStatus.OK)
               .message(MessageKeys.GET_SUCCESS)
               .data(productDetailResponse)
               .build();
    }

    @Override
    @Transactional
    public BaseResponse<Void> insertProduct(ProductDTO productDTO)  {
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException(MessageKeys.CATEGORY_NOT_FOUND));
        if (existingCategory.getParentCategory() == null){
            throw new ConstraintViolationException(MessageKeys.CATEGORY_NOT_SUBCATEGORY);
        }
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        productRepository.save(product);
        return BaseResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message(MessageKeys.INSERTED_SUCCESS)
                .build();
    }

    @Override
    public BaseResponse<Void> updateProduct(ProductDTO productDTO, Long productId){
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_NOT_FOUND));
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                 .orElseThrow(() -> new NotFoundException(MessageKeys.CATEGORY_NOT_FOUND));
        if (existingCategory.getParentCategory() == null){
            throw new ConstraintViolationException(MessageKeys.CATEGORY_NOT_SUBCATEGORY);
        }
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setThumbnail(productDTO.getThumbnail());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setCategory(existingCategory);
        productRepository.save(existingProduct);
        return BaseResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message(MessageKeys.UPDATED_SUCCESS)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<Void> deleteProduct(Long productId){
        Product existingProduct = productRepository.findById(productId)
                        .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_NOT_FOUND));
       productRepository.deleteById(productId);
       return BaseResponse.<Void>builder()
               .success(true)
               .status(HttpStatus.OK)
               .message(MessageKeys.DELETED_SUCCESS)
               .build();
    }
}
