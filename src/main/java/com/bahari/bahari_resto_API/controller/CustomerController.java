package com.bahari.bahari_resto_API.controller;

import com.bahari.bahari_resto_API.constant.EndPointApp;
import com.bahari.bahari_resto_API.model.dto.request.CustomerRequest;
import com.bahari.bahari_resto_API.model.dto.response.CommonResponse;
import com.bahari.bahari_resto_API.model.dto.response.CustomerResponse;
import com.bahari.bahari_resto_API.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointApp.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.create(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new Customer")
                        .data(customerResponse)
                        .build());
    }
}
