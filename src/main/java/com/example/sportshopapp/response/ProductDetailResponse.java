package com.example.sportshopapp.response;

import com.example.sportshopapp.dtos.ProductSizeDTO;
import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.models.ProductImages;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private Long id;
    private String name;
    private Double price;
    private String thumbnail;
    private String description;
    private int quantity;
    @JsonProperty(namespace = "category_id")
    private Long categoryId;
    @JsonProperty("product_images")
    private List<ProductImages> productImages;
    @JsonProperty("product_sizes")
    private List<ProductSizeDTO> productSizes;

    public static ProductDetailResponse convertToProductDetailResponse(Product product){
        ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .productImages(product.getProductImages())
                .build();
        List<ProductSizeDTO> sizeDTOList = product.getProductSizes().stream()
                .map(ProductSizeDTO::convertToProductSizeDTO)
                .collect(Collectors.toList());
        productDetailResponse.setProductSizes(sizeDTOList);
        return productDetailResponse;
    }
}
