package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.exceptions.OrderNotFoundException;
import com.bahari.bahari_resto_API.exceptions.OrderProcessingException;
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
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = RollbackException.class)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Valid
    private void validateOrderRequest(OrderRequest x){
        if(x == null){
            throw new IllegalArgumentException("OrderRequest cannot be null");
        }
        if(x.getDateTime() == null){
            throw new IllegalArgumentException("OrderRequest cannot be null");
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
                .orElseThrow(() -> new NoSuchElementException(String.format("Customer not found with id : %s",
                        orderRequest.getCustomerId())));

        //TODO 3 : Create / Build Order Entity
        Order order = Order.builder().build();
        order.setDateTime(orderRequest.getDateTime());
        order.setCustomerId(customer);

        //TODO 4 : Set List<OrderDetails>
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for(OrderDetailsRequest orderDetailsRequest : orderRequest.getOrderDetails()){
            Product product = productRepository.findById(orderDetailsRequest.getProductId())
                    .orElseThrow(() -> new NoSuchElementException(String.format("product id not found : %s" ,orderDetailsRequest.getProductId())));
            Integer totalPrices = product.getPrice() * orderDetailsRequest.getQuantity();
            OrderDetails orderDetails = OrderDetails.builder()
                    .order(order)
                    .product(product)
                    .quantity(orderDetailsRequest.getQuantity())
                    .totalPrice(totalPrices)
                    .build();
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);

        //TODO 5 : Save Order Entity
        Order savedOrder = orderRepository.saveAndFlush(order);

        //TODO 6 : Build Response
        List<OrderDetailsResponse> detailsResponses = orderDetailsList.stream()
                .map(od -> OrderDetailsResponse.builder()
                        .id(od.getId())
                        .orderId(od.getOrder().getId())
                        .productId(od.getProduct().getId())
                        .quantity(od.getQuantity())
                        .totalPrice(od.getTotalPrice())
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
                        .totalPrice(orderDetails.getTotalPrice())
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
        validateOrderRequest(orderRequest);

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(String.format("Customer not found with id : %s" ,
                        orderRequest.getCustomerId())));

        Order order = orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no orders found"));
        List<OrderDetails> orderDetails = orderDetailRepository.findByOrder(order);
        List<OrderDetailsResponse> orderDetailsResponses = orderDetails.stream()
                        .map(orderDetailsMap -> OrderDetailsResponse.builder()
                                .id(orderDetailsMap.getId())
                                .orderId(orderDetailsMap.getOrder().getId())
                                .quantity(orderDetailsMap.getQuantity())
                                .productId(orderDetailsMap.getProduct().getId())
                                .totalPrice(orderDetailsMap.getTotalPrice())
                                .build()).toList();

        order.setDateTime(orderRequest.getDateTime());
        order.setCustomerId(customer);
        order.setOrderDetails(orderDetails);

        orderRepository.saveAndFlush(order);

        return OrderResponse.builder()
                .id(order.getId())
                .dateTime(order.getDateTime())
                .customerId(customer.getId())
                .orderDetails(orderDetailsResponses)
                .build();
    }

    @Override
    public void delete(String id) {
        try {
            Order orderDelete = orderRepository.findById(id).orElseThrow(
                    () -> new NoSuchElementException(String.format("not found such order with id : %s", id)));
            orderRepository.delete(orderDelete);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException(String.format("Order not found with id : %s" , id));
        } catch (Exception e ){
            throw new OrderProcessingException(String.format("Error deleting order with the id : %s" , id));
        }
    }
}
