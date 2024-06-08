package com.bahari.bahari_resto_API.controller;

import com.bahari.bahari_resto_API.constant.EndPointApp;
import com.bahari.bahari_resto_API.model.dto.request.CustomerRequest;
import com.bahari.bahari_resto_API.model.dto.response.CommonResponse;
import com.bahari.bahari_resto_API.model.dto.response.CustomerResponse;
import com.bahari.bahari_resto_API.model.entity.Customer;
import com.bahari.bahari_resto_API.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    private ResponseEntity<?> getAll(){
        List<Customer> customerData = customerService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch data")
                        .data(customerData));
    }

    @GetMapping(EndPointApp.GET_BY_ID)
    private ResponseEntity<?> getById(@PathVariable String id){
        CustomerResponse customerResponse = customerService.getId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch data")
                        .data(customerResponse));
    }

    @PutMapping(EndPointApp.PUT_BY_ID)
    private ResponseEntity<?> update(@PathVariable String id,@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.update(id,customerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update data")
                        .data(customerResponse));
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    private void delete(@PathVariable String id){
        customerService.delete(id);
        ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete data"));
    }
}
