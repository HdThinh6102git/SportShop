package com.example.sportshopapp.response;

import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.dtos.ProductSizeDTO;
import com.example.sportshopapp.dtos.SizeDTO;
import com.example.sportshopapp.models.Cart;
import com.example.sportshopapp.models.ProductSize;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long id;
    private ProductDTO productDTO;
    private SizeDTO sizeDTO;
    private Integer quantity;
    @JsonProperty("is_available")
    private Boolean isAvailable = false;

    public static CartItemResponse convertToCartResponse(Cart cart, ProductSize productSize){
        Optional<SizeDTO> sizeDTO = Optional.ofNullable(cart.getSize()).map(SizeDTO::convertToSizeDTO);
        int availableQuantity = (cart.getSize() != null) ? productSize.getQuantity(): cart.getProduct().getQuantity();
        return CartItemResponse.builder()
                .id(cart.getId())
                .productDTO(ProductDTO.convertToProductDTO(cart.getProduct()))
                .sizeDTO(sizeDTO.orElse(null))
                .quantity(availableQuantity)
                .isAvailable(availableQuantity > 0)
                .build();
    }
}
