package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.dto.response.CustomerResponse;
import com.tabletrack.table_track_API.model.entity.order.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
}
