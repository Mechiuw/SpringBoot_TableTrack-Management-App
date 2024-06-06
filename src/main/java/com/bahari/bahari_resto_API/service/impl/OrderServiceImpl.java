package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
import com.bahari.bahari_resto_API.model.entity.Order;
import com.bahari.bahari_resto_API.repository.OrderRepository;
import com.bahari.bahari_resto_API.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private void validateOrderRequest(OrderRequest x){
        if(x == null){
            throw new IllegalArgumentException("OrderRequest cannot be null");
        }
        if(x.getDateTime() == null){
            throw new IllegalArgumentException("OrderRequest cannot be null");
        }
        if(x.getOrderDetails() == null || x.getOrderDetails().isEmpty()){
            throw new IllegalArgumentException("order details cannot be null or empty");
        }
        if(x.getCustomerId() == null || x.getCustomerId().isEmpty()){
            throw new IllegalArgumentException("customer id cannot be null");
        }
    }

    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        validateOrderRequest(orderRequest);



        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public OrderResponse getById(String id) {
        return null;
    }

    @Override
    public OrderResponse update(String id, OrderRequest orderRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
