package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {
}
