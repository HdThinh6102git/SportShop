package com.example.sportshopapp.services;

import com.example.sportshopapp.models.OrderDetail;
import com.example.sportshopapp.response.BaseResponse;

import java.util.List;

public interface IOrderDetailService {
    BaseResponse<List<OrderDetail>> getOrderDetails();
    BaseResponse<OrderDetail> getOrderDetail();
    BaseResponse<Void> updateOrderDetail();
    BaseResponse<Void> deleteOrderDetail();
}
