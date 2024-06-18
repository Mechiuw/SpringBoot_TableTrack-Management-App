package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.order.Order;
import com.tabletrack.table_track_API.model.entity.order.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails,String> {
    List<OrderDetails> findByOrder(Order order);
}
