package com.example.sportshopapp.services;

import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.ProductDetailResponse;
import com.example.sportshopapp.response.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    BaseResponse<ProductListResponse> getProducts(String keyword, Long category_id, PageRequest pageRequest) throws Exception;
    BaseResponse<ProductDetailResponse> getDetailProduct(Long productId) throws Exception;

    BaseResponse<Void> insertProduct(ProductDTO productDTO) throws Exception;

    BaseResponse<Void> updateProduct(ProductDTO productDTO, Long productId) throws Exception;

    BaseResponse<Void> deleteProduct(Long productId) throws Exception;
}
