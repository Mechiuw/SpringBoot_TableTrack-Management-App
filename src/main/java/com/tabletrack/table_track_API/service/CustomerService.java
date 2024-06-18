package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.CustomerRequest;
import com.tabletrack.table_track_API.model.dto.response.CustomerResponse;
import com.tabletrack.table_track_API.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    List<Customer> getAll();
    CustomerResponse getId(String id);
    CustomerResponse update(String id, CustomerRequest customerRequest);
    void delete(String id);
}
