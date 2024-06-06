package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
