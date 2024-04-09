package com.example.sportshopapp.services.Impl;

import com.example.sportshopapp.models.OrderDetail;
import com.example.sportshopapp.response.BaseResponse;
import com.example.sportshopapp.services.IOrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Override
    public BaseResponse<List<OrderDetail>> getOrderDetails() {
        return null;
    }

    @Override
    public BaseResponse<OrderDetail> getOrderDetail() {
        return null;
    }

    @Override
    public BaseResponse<Void> updateOrderDetail() {
        return null;
    }

    @Override
    public BaseResponse<Void> deleteOrderDetail() {
        return null;
    }
}
