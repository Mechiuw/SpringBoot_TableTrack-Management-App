package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.Container;
import com.tabletrack.table_track_API.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container,String> {
    List<Container> findByWarehouseId(Warehouse warehouseId);
}
