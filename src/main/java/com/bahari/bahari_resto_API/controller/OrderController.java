package com.bahari.bahari_resto_API.controller;

import com.bahari.bahari_resto_API.constant.EndPointApp;
import com.bahari.bahari_resto_API.model.dto.request.OrderRequest;
import com.bahari.bahari_resto_API.model.dto.response.CommonResponse;
import com.bahari.bahari_resto_API.model.dto.response.OrderResponse;
import com.bahari.bahari_resto_API.repository.OrderRepository;
import com.bahari.bahari_resto_API.service.OrderService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointApp.ORDER)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.create(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created order transaction")
                        .data(orderResponse)
                        .build());
    }
}
