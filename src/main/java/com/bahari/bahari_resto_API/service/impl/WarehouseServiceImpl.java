package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.WarehouseRequest;
import com.bahari.bahari_resto_API.model.dto.response.WarehouseResponse;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
import com.bahari.bahari_resto_API.repository.WarehouseRepository;
import com.bahari.bahari_resto_API.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public WarehouseResponse create(WarehouseRequest warehouseRequest) {
        return null;
    }

    @Override
    public WarehouseResponse getById(String id) {
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        return null;
    }

    @Override
    public WarehouseResponse update(String id, WarehouseRequest warehouseRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
