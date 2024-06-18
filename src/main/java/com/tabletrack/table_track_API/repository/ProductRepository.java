package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.order.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
