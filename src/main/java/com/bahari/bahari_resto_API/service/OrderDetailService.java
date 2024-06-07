package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.OrderDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;

import java.util.List;

public interface OrderDetailService {
    OrderDetailsResponse create(OrderDetailsRequest orderDetailsRequest);
    List<OrderDetails> getAll();
    OrderDetailsResponse getById(String id);
    OrderDetailsResponse update(String id, OrderDetailsRequest orderDetailsRequest);
    void delete(String id);
}
