package com.example.sportshopapp.services.Impl;


import com.example.sportshopapp.common.MessageKeys;
import com.example.sportshopapp.dtos.CartItemDTO;
import com.example.sportshopapp.exceptions.ConstraintViolationException;
import com.example.sportshopapp.exceptions.NotFoundException;
import com.example.sportshopapp.models.*;
import com.example.sportshopapp.repositories.*;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.response.CartItemResponse;
import com.example.sportshopapp.services.ICartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final SizeRepository sizeRepository;
    private final ProductSizeRepository productSizeRepository;

    @Override
    @Transactional
    public BaseResponse<Void> addToCartOrUpdate(Long userId, Long productId, Long sizeId, int quantity) {
        User existingUser = userRepository.findById(userId).orElse(null);
        Product existingProduct = productRepository.findById(productId).orElse(null);

        if (existingUser == null) {
            throw new NotFoundException(MessageKeys.USER_NOT_FOUND);
        }

        if (existingProduct == null || (sizeId == null && productSizeRepository.existsProductSizeByProduct(existingProduct))) {
            throw new NotFoundException(MessageKeys.PRODUCT_NOT_FOUND);
        }

        Size existingSize = (sizeId != null) ? sizeRepository.findById(sizeId).orElse(null) : null;
        ProductSize productSize = (sizeId != null) ? productSizeRepository.findProductSizeByProductAndSize(existingProduct, existingSize).orElse(null) : null;

        if (sizeId != null && productSize == null) {
            throw new NotFoundException(MessageKeys.PRODUCT_SIZE_NOT_FOUND);
        }

        int availableQuantity = (sizeId != null) ? productSize.getQuantity() : existingProduct.getQuantity();

        if (availableQuantity >= quantity) {
            Cart existingCartItem = (sizeId != null) ?
                    cartRepository.getCartByUserAndProductAndSize(existingUser, existingProduct, existingSize).orElse(null) :
                    cartRepository.getCartByUserAndProduct(existingUser, existingProduct).orElse(null);

            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                cartRepository.save(existingCartItem);
            } else {
                Cart newCartItem = Cart.builder()
                        .user(existingUser)
                        .product(existingProduct)
                        .size(existingSize)
                        .quantity(quantity)
                        .build();
                cartRepository.save(newCartItem);
            }

            return BaseResponse.<Void>builder()
                    .success(true)
                    .status(HttpStatus.OK)
                    .message(MessageKeys.INSERTED_SUCCESS)
                    .build();
        } else {
            throw new ConstraintViolationException(MessageKeys.INSUFFICIENT_QUANTITY);
        }
    }

    @Override
    @Transactional
    public BaseResponse<Void> deleteCartItems(Long userId, List<CartItemDTO> cartItemDTOS) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(MessageKeys.USER_NOT_FOUND));
        for (CartItemDTO cartItemDTO : cartItemDTOS){
            Product existingProduct = productRepository.findById(cartItemDTO.getProductId())
                    .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_NOT_FOUND));
            // Kiểm tra sự tồn tại của sản phẩm trong giỏ hàng của người dùng
            if (!cartRepository.existsByUserAndProduct(existingUser, existingProduct)) {
                throw new NotFoundException(MessageKeys.PRODUCT_NOT_IN_USER_CART);
            }
            if (cartItemDTO.getSizeId() != null){
                Size existingSize = sizeRepository.findById(cartItemDTO.getSizeId())
                        .orElseThrow(() -> new NotFoundException(MessageKeys.SIZE_NOT_FOUND));
                ProductSize existingProductSize = productSizeRepository.findProductSizeByProductAndSize(existingProduct,existingSize)
                        .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_SIZE_NOT_FOUND));
                // Kiểm tra sự tồn tại của sản phẩm-size trong giỏ hàng của người dùng
                if (!cartRepository.existsByUserAndProductAndSize(existingUser, existingProduct, existingSize)) {
                    throw new NotFoundException(MessageKeys.PRODUCT_SIZE_NOT_IN_USER_CART);
                }
                cartRepository.deleteCartsByUserAndProductAndSize(existingUser,existingProduct,existingSize);
            }else {
                if (productSizeRepository.existsProductSizeByProduct(existingProduct)){
                    throw new NotFoundException(MessageKeys.PRODUCT_SIZE_NULL_INPUT);
                }
                cartRepository.deleteCartsByUserAndProduct(existingUser,existingProduct);
            }
        }
        return BaseResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message(MessageKeys.DELETED_SUCCESS)
                .build();
    }

    @Override
    public BaseResponse<List<CartItemResponse>> getCartItems(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(MessageKeys.USER_NOT_FOUND));
        List<Cart> carts = cartRepository.getCartsByUser(existingUser);

        List<CartItemResponse> cartItemResponses = carts.stream()
                .map(cart -> {
                    Optional<ProductSize> productSizeOpt = Optional.ofNullable(cart.getSize())
                            .map(size -> productSizeRepository.findProductSizeByProductAndSize(cart.getProduct(), size)
                                    .orElseThrow(() -> new NotFoundException(MessageKeys.PRODUCT_SIZE_NOT_FOUND)));

                    return CartItemResponse.convertToCartResponse(cart, productSizeOpt.orElse(null));
                })
                .toList();

        return BaseResponse.<List<CartItemResponse>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message(MessageKeys.GET_CART_ITEMS_SUCCESS)
                .data(cartItemResponses)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<Void> clearCart(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(MessageKeys.USER_NOT_FOUND));
        cartRepository.deleteCartByUser(existingUser);
        return BaseResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message(MessageKeys.CLEAR_CART_SUCCESS)
                .build();
    }
}
