package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
import com.bahari.bahari_resto_API.model.entity.Customer;
import com.bahari.bahari_resto_API.model.entity.Order;
import com.bahari.bahari_resto_API.repository.CustomerRepository;
import com.bahari.bahari_resto_API.repository.OrderRepository;
import com.bahari.bahari_resto_API.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

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
        //TODO 1 : Input validation
        validateOrderRequest(orderRequest);

        //TODO 2 : Retrieve Customer
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id : " +
                        orderRequest.getCustomerId()));

        //TODO 3 : Create / Build Order Entity
        Order order = Order.builder().build();
        order.setDateTime(orderRequest.getDateTime());
        order.setCustomerId(customer);

        //TODO 4 : Set Order Details

        //TODO 5 : Save Order Entity

        //TODO 6 : Build Response

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
