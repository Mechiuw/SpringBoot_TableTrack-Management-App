package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {
}
