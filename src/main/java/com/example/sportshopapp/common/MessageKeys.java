package com.example.sportshopapp.common;

public class MessageKeys {
    public static final String SUCCESS = "Thành công";
    public static final String FAILED = "Thất bại";
    public static final String INSERTED_SUCCESS= "Insert successfully";
    public static final String DELETED_SUCCESS = "Delete successfully";
    public static final String UPDATED_SUCCESS = "Update successfully";
    public static final String GET_SUCCESS = "Get successfully";
    public static final String ALREADY_CATEGORY_NAME = "Tên danh mục đã tồn tại";
    public static final String PARENT_CATEGORY_NOT_EXISTING = "Danh mục cha không tồn tại";
    public static final String CATEGORY_NOT_EXISTING = "Danh mục có id là {id} không tồn tại";
    public static final String VALIDATE_ID = "Id phải là một số nguyên dương";
    /*Not-Found*/
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String SIZE_NOT_FOUND = "Size not found";
    public static final String PRODUCT_NOT_IN_USER_CART = "Product not in user cart";
    public static final String PRODUCT_SIZE_NOT_IN_USER_CART = "Product size not in user cart";
    public static final String PRODUCT_SIZE_NULL_INPUT = "Product size cannot be null for an existing product with sizes.";

    /*Not-Found*/

    /*Constraint*/
    public static final String CATEGORY_NOT_SUBCATEGORY = "Category must be a subcategory";
    public static final String PRODUCT_SIZE_NOT_FOUND = "The product does not have the corresponding size";
    public static final String INSUFFICIENT_QUANTITY = "The quantity of products in stock is not enough";
    /*Constraint*/

    public static final String GET_CART_ITEMS_SUCCESS = "Get cart items successfully";
    public static final String CLEAR_CART_SUCCESS = "Clear cart successfully";
    public static final String DELETE_CART_ITEMS_SUCCESS = "Delete cart items successfully";
    public static final String INTERNAL_SERVER_ERROR = "An error occurred while processing the request";
}
