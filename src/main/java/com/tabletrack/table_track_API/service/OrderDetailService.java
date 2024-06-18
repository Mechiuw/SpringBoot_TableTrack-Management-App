package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.OrderDetailsRequest;
import com.tabletrack.table_track_API.model.dto.response.OrderDetailsResponse;
import com.tabletrack.table_track_API.model.entity.order.OrderDetails;

import java.util.List;

public interface OrderDetailService {
    OrderDetailsResponse create(OrderDetailsRequest orderDetailsRequest);
    List<OrderDetails> getAll();
    OrderDetailsResponse getById(String id);
    OrderDetailsResponse update(String id, OrderDetailsRequest orderDetailsRequest);
    void delete(String id);
}
