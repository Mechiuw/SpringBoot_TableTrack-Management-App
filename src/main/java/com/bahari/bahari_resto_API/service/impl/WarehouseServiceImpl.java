package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.WarehouseRequest;
import com.bahari.bahari_resto_API.model.dto.response.WarehouseResponse;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
import com.bahari.bahari_resto_API.repository.WarehouseRepository;
import com.bahari.bahari_resto_API.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public WarehouseResponse create(WarehouseRequest warehouseRequest) {
        Warehouse warehouse = Warehouse.builder()
                .name(warehouseRequest.getName())
                .address(warehouseRequest.getAddress())
                .phoneNum(warehouseRequest.getPhoneNum())
                .country(warehouseRequest.getCountry())
                .build();
        warehouseRepository.save(warehouse);
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .address(warehouse.getAddress())
                .phoneNum(warehouse.getPhoneNum())
                .eCountry(warehouse.getCountry())
                .build();
    }

    @Override
    public WarehouseResponse getById(String id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id : %s",id)));
        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .phoneNum(warehouse.getPhoneNum())
                .eCountry(warehouse.getCountry())
                .build();
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
