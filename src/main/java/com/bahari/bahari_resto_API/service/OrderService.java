package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.OrderRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
import com.bahari.bahari_resto_API.model.entity.Order;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);
    List<Order> getAll();
    OrderResponse getById(String id);
    OrderResponse update(String id,OrderRequest orderRequest);
    void delete(String id);
}
