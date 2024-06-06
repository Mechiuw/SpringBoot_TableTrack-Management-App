package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
}
