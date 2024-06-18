package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.WarehouseRequest;
import com.tabletrack.table_track_API.model.dto.response.ContainerResponse;
import com.tabletrack.table_track_API.model.dto.response.WarehouseResponse;
import com.tabletrack.table_track_API.model.entity.product_import.Container;
import com.tabletrack.table_track_API.model.entity.product_import.Warehouse;

import java.util.List;

public interface WarehouseService {
    WarehouseResponse create(WarehouseRequest warehouseRequest);

    WarehouseResponse getById(String id);

    List<Warehouse> getAll();

    WarehouseResponse update(String id,WarehouseRequest warehouseRequest);

    void delete(String id);

    List<Container> listAllContainer(String warehouseId);

    ContainerResponse findContainer(String warehouseId,String containerId);
    ContainerResponse addContainer(String warehouseId,String containerId);
}
