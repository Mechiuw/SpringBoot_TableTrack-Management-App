package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetails,String> {
}
