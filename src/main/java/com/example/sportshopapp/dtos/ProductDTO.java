package com.example.sportshopapp.dtos;


import com.example.sportshopapp.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotNull(message = "Product Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
    @NotNull
    private String thumbnail;
    @NotNull
    private String description;
    @NotNull
    @JsonProperty("category_id")
    private Long categoryId;

    public static ProductDTO convertToProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
