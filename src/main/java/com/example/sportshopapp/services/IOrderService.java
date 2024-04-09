package com.example.sportshopapp.services;

import com.example.sportshopapp.models.BaseEntity;
import com.example.sportshopapp.models.Order;
import com.example.sportshopapp.models.OrderDetail;
import com.example.sportshopapp.response.BaseResponse;

public interface IOrderService {
    BaseResponse<Order> getOrders();
    BaseResponse<OrderDetail> createOrderDetail();
    BaseResponse<Void> updateOrder();
}
