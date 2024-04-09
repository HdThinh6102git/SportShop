package com.example.sportshopapp.services.Impl;

import com.example.sportshopapp.models.Order;
import com.example.sportshopapp.models.OrderDetail;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.services.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Override
    public BaseResponse<Order> getOrders() {
        return null;
    }

    @Override
    public BaseResponse<OrderDetail> createOrderDetail() {
        return null;
    }

    @Override
    public BaseResponse<Void> updateOrder() {
        return null;
    }
}
