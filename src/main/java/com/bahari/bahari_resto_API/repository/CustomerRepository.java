package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
