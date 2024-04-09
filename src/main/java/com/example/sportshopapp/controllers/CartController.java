package com.example.sportshopapp.controllers;

import com.example.sportshopapp.common.ValidationErrorHandler;
import com.example.sportshopapp.dtos.CartItemDTO;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.CartItemResponse;
import com.example.sportshopapp.services.Impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ValidationErrorHandler validationErrorHandler;
    @PostMapping("/manage-item")
    private ResponseEntity<BaseResponse<Void>> addToCartOrUpdate(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "product_id") Long productId,
            @RequestParam(name = "size_id", required = false) Long sizeId,
            @RequestParam(name = "quantity", defaultValue = "1") int quantity
    ){
        BaseResponse<Void> response = cartService.addToCartOrUpdate(userId,productId,sizeId,quantity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/remove-items/{user_id}")
    private ResponseEntity<BaseResponse<Void>> deleteCartItems(
            @PathVariable("user_id") Long userId,
            @RequestBody List<CartItemDTO> cartItemDTOS,
            BindingResult result
            ){
        if (result.hasErrors()){
            BaseResponse<Void> errorResponse = validationErrorHandler.handleValidationErrors(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        BaseResponse<Void> response = cartService.deleteCartItems(userId,cartItemDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @DeleteMapping("/clear/{user_id}")
    private ResponseEntity<BaseResponse<Void>> clearCart(
            @PathVariable("user_id") Long userId
    ){
        BaseResponse<Void> response = cartService.clearCart(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("{user_id}")
    private ResponseEntity<BaseResponse<List<CartItemResponse>>> getCartItems(
            @PathVariable("user_id") Long userId
    ){
        BaseResponse<List<CartItemResponse>> response = cartService.getCartItems(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
