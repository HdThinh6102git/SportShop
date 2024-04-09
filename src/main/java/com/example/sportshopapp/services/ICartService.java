package com.example.sportshopapp.services;

import com.example.sportshopapp.dtos.CartItemDTO;
import com.example.sportshopapp.models.Cart;
import com.example.sportshopapp.models.Product;
import com.example.sportshopapp.models.Size;
import com.example.sportshopapp.models.User;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.CartItemResponse;

import java.util.List;

public interface ICartService {
    BaseResponse<Void> addToCartOrUpdate(Long userId, Long productId, Long sizeId, int quantity);
    BaseResponse<Void> deleteCartItems(Long userId, List<CartItemDTO> cartItemDTOS);
    BaseResponse<List<CartItemResponse>> getCartItems(Long userId);
    BaseResponse<Void> clearCart(Long userId);
}
