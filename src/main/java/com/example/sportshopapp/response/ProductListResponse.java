package com.example.sportshopapp.response;

import com.example.sportshopapp.dtos.ProductDTO;
import com.example.sportshopapp.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    @JsonProperty("product_list")
    private List<ProductDTO> productList;
    @JsonProperty("total_pages")
    private int totalPages;
}
