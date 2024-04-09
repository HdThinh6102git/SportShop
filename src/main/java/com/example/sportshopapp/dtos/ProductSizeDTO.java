package com.example.sportshopapp.dtos;

import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.models.ProductSize;
import jakarta.validation.constraints.Min;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeDTO {
    private Long sizeId;
    private String sizeName;
    @Min(value = 0, message = "Quantity must be greater than 0")
    private int quantity;

    public static ProductSizeDTO convertToProductSizeDTO(ProductSize productSize){
        return ProductSizeDTO.builder()
                .sizeId(productSize.getSize().getId())
                .sizeName(productSize.getSize().getName())
                .quantity(productSize.getQuantity())
                .build();
    }
}
