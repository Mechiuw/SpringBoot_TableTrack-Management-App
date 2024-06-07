package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.request.OrderRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
import com.bahari.bahari_resto_API.model.entity.Customer;
import com.bahari.bahari_resto_API.model.entity.Order;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import com.bahari.bahari_resto_API.model.entity.Product;
import com.bahari.bahari_resto_API.repository.CustomerRepository;
import com.bahari.bahari_resto_API.repository.OrderDetailRepository;
import com.bahari.bahari_resto_API.repository.OrderRepository;
import com.bahari.bahari_resto_API.repository.ProductRepository;
import com.bahari.bahari_resto_API.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

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

        //TODO 4 : Set List<OrderDetails>
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for(OrderDetailsRequest orderDetailsRequest : orderRequest.getOrderDetails()){
            Product product = productRepository.findById(orderDetailsRequest.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("product id not found : " + orderDetailsRequest.getProductId()));
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProduct(product);
            orderDetails.setQuantity(orderDetailsRequest.getQuantity());
            orderDetails.setOrder(order);
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);

        //TODO 5 : Save Order Entity
        Order savedOrder = orderRepository.saveAndFlush(order);

        //TODO 6 : Build Response
        List<OrderDetailsResponse> detailsResponses = orderDetailsList.stream()
                .map(od -> OrderDetailsResponse.builder()
                        .orderId(od.getId())
                        .productId(od.getProduct().getId())
                        .quantity(od.getQuantity())
                        .build()).toList();

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .dateTime(savedOrder.getDateTime())
                .customerId(savedOrder.getCustomerId().getId())
                .orderDetails(detailsResponses)
                .build();
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponse getById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no orders found"));
        List<OrderDetails> orderDetailsList = orderDetailRepository.findByOrder(order);
        List<OrderDetailsResponse> orderDetailsResponses = orderDetailsList.stream()
                .map(orderDetails -> OrderDetailsResponse.builder()
                        .id(orderDetails.getId())
                        .orderId(order.getId())
                        .quantity(orderDetails.getQuantity())
                        .productId(orderDetails.getProduct().getId())
                        .build()).toList();
        return OrderResponse.builder()
                .id(order.getId())
                .dateTime(order.getDateTime())
                .customerId(order.getCustomerId().getId())
                .orderDetails(orderDetailsResponses)
                .build();
    }

    @Override
    public OrderResponse update(String id, OrderRequest orderRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
