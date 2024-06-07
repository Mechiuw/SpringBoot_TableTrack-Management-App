package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import com.bahari.bahari_resto_API.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetailsResponse create(OrderDetailsRequest orderDetailsRequest) {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() {
        return null;
    }

    @Override
    public OrderDetailsResponse getById(String id) {
        return null;
    }

    @Override
    public OrderDetailsResponse update(String id, OrderDetailsRequest orderDetailsRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
