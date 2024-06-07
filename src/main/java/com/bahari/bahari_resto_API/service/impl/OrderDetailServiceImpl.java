package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.OrderDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
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
                .orElseThrow(() -> new NoSuchElementException("not found such orders"));

        Product product = productRepository.findById(orderDetailsRequest.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Not found such product"));

        OrderDetails orderDetails = OrderDetails.builder()
                .order(order)
                .product(product)
                .quantity(orderDetailsRequest.getQuantity())
                .build();

        OrderDetails savedOrderDetails = orderDetailRepository.save(orderDetails);

        return OrderDetailsResponse.builder()
                .id(savedOrderDetails.getId())
                .orderId(savedOrderDetails.getId())
                .productId(savedOrderDetails.getProduct().getId())
                .quantity(savedOrderDetails.getQuantity())
                .build();
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
