package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.WarehouseRequest;
import com.bahari.bahari_resto_API.model.dto.response.WarehouseResponse;
import com.bahari.bahari_resto_API.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    WarehouseResponse create(WarehouseRequest warehouseRequest);

    WarehouseResponse getById(String id);

    List<Warehouse> getAll();

    WarehouseResponse update(String id,WarehouseRequest warehouseRequest);

    void delete(String id);
}
