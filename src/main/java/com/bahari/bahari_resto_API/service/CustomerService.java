package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.CustomerRequest;
import com.bahari.bahari_resto_API.model.dto.response.CustomerResponse;
import com.bahari.bahari_resto_API.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    List<Customer> getAll();
    CustomerResponse getId(String id);
    CustomerResponse update(String id, CustomerRequest customerRequest);
    void delete(String id);
}
