package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.model.dto.request.CustomerRequest;
import com.tabletrack.table_track_API.model.dto.response.CustomerResponse;
import com.tabletrack.table_track_API.model.entity.Customer;
import com.tabletrack.table_track_API.repository.CustomerRepository;
import com.tabletrack.table_track_API.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .phoneNum(customerRequest.getPhoneNum())
                .build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponse getId(String id){
        Customer customer = customerRepository.findById(id).orElseThrow();
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public CustomerResponse update(String id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNum(customerRequest.getPhoneNum());
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            customer.ifPresent(customerRepository::delete);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
