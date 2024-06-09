package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.Order;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import com.bahari.bahari_resto_API.model.entity.Product;
import com.bahari.bahari_resto_API.repository.OrderDetailRepository;
import com.bahari.bahari_resto_API.repository.OrderRepository;
import com.bahari.bahari_resto_API.repository.ProductRepository;
import com.bahari.bahari_resto_API.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDetailsResponse create(OrderDetailsRequest orderDetailsRequest) {
        Order order = orderRepository.findById(orderDetailsRequest.getOrderId())
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        Product product = productRepository.findById(orderDetailsRequest.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        OrderDetails orderDetails = OrderDetails.builder()
                .order(order)
                .product(product)
                .quantity(orderDetailsRequest.getQuantity())
                .totalPrice(product.getPrice() * orderDetailsRequest.getQuantity())
                .build();

        OrderDetails savedOrderDetails = orderDetailRepository.save(orderDetails);

        return OrderDetailsResponse.builder()
                .id(savedOrderDetails.getId())
                .orderId(savedOrderDetails.getOrder().getId())
                .productId(savedOrderDetails.getProduct().getId())
                .quantity(savedOrderDetails.getQuantity())
                .totalPrice(savedOrderDetails.getTotalPrice())
                .build();
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetailsResponse getById(String id) {
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order Details not found"));
        return OrderDetailsResponse.builder()
                .id(orderDetails.getId())
                .quantity(orderDetails.getQuantity())
                .productId(orderDetails.getProduct().getId())
                .orderId(orderDetails.getOrder().getId())
                .totalPrice(orderDetails.getTotalPrice())
                .build();
    }

    @Override
    public OrderDetailsResponse update(String id, OrderDetailsRequest orderDetailsRequest) {
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "not found any orderDetails with id : %s",id
                )));

        Product product = productRepository.findById(orderDetailsRequest.getProductId())
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "not found any product with id : %s",orderDetailsRequest.getProductId()
                )));
        Order order = orderRepository.findById(orderDetailsRequest.getOrderId())
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "not found any order with id : %s",orderDetailsRequest.getOrderId()
                )));

        orderDetails.setQuantity(orderDetailsRequest.getQuantity());
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setTotalPrice(orderDetailsRequest.getQuantity() * product.getPrice());

        OrderDetails savedOrderDetails = orderDetailRepository.saveAndFlush(orderDetails);

        return OrderDetailsResponse.builder()
                .id(savedOrderDetails.getId())
                .quantity(savedOrderDetails.getQuantity())
                .orderId(savedOrderDetails.getOrder().getId())
                .productId(savedOrderDetails.getProduct().getId())
                .totalPrice(savedOrderDetails.getTotalPrice())
                .build();
    }

    @Override
    public void delete(String id) {
        OrderDetails orderDetails = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "no such order detail found with id : %s",id
                )));

        orderDetailRepository.delete(orderDetails);
    }
}
