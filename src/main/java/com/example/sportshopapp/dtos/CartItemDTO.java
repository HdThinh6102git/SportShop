package com.example.sportshopapp.dtos;

import com.example.sportshopapp.models.Cart;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    @NotNull(message = "Product ID cannot be null")
    @JsonProperty("product_id")
    private Long productId;

    @Null(message = "Size ID must be null or a valid size ID")
    @JsonProperty("size_id")
    private Long sizeId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be a positive integer")
    private Integer quantity;

    public static CartItemDTO convertToCartItem(Cart cart){
        return CartItemDTO.builder()
                .productId(cart.getProduct().getId())
                .sizeId(cart.getSize().getId())
                .quantity(cart.getQuantity())
                .build();
    }
}
