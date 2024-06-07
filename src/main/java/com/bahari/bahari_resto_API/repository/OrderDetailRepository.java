package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Order;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails,String> {
    List<OrderDetails> findByOrder(Order order);
}
