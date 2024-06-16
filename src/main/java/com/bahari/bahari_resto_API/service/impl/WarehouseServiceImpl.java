package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ContainerRequest;
import com.bahari.bahari_resto_API.model.dto.request.WarehouseRequest;
import com.bahari.bahari_resto_API.model.dto.response.ContainerResponse;
import com.bahari.bahari_resto_API.model.dto.response.WarehouseResponse;
import com.bahari.bahari_resto_API.model.entity.Container;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
import com.bahari.bahari_resto_API.repository.ContainerRepository;
import com.bahari.bahari_resto_API.repository.WarehouseRepository;
import com.bahari.bahari_resto_API.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ContainerRepository containerRepository;

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
        return warehouseRepository.findAll();
    }

    @Override
    public WarehouseResponse update(String id, WarehouseRequest warehouseRequest) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id : %s",id)));
        warehouse.setName(warehouseRequest.getName());
        warehouse.setAddress(warehouseRequest.getAddress());
        warehouse.setCountry(warehouseRequest.getCountry());
        warehouse.setPhoneNum(warehouseRequest.getPhoneNum());
        Warehouse savedWarehouse = warehouseRepository.saveAndFlush(warehouse);
        return WarehouseResponse.builder()
                .id(savedWarehouse.getId())
                .name(savedWarehouse.getName())
                .address(savedWarehouse.getAddress())
                .eCountry(savedWarehouse.getCountry())
                .phoneNum(savedWarehouse.getPhoneNum())
                .build();
    }

    @Override
    public void delete(String id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found warehouse with id : %s",id)));
        warehouseRepository.delete(warehouse);
    }

    public List<Container> listAllContainer(String id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException(String.format("no such warehouse found with id : %s",id)));
        List<Container> containerList = containerRepository.findAll()
                .stream().filter(x -> x.getWarehouseId().getId().equals(warehouse.getId())).toList();
        return containerList;
    }
}
