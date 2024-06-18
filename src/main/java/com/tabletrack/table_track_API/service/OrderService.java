package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.OrderRequest;
import com.tabletrack.table_track_API.model.dto.response.OrderResponse;
import com.tabletrack.table_track_API.model.entity.Order;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);
    List<Order> getAll();
    OrderResponse getById(String id);
    OrderResponse update(String id,OrderRequest orderRequest);
    void delete(String id);
}
