package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.model.dto.request.WarehouseRequest;
import com.tabletrack.table_track_API.model.dto.response.ContainerResponse;
import com.tabletrack.table_track_API.model.dto.response.WarehouseResponse;
import com.tabletrack.table_track_API.model.entity.product_import.Container;
import com.tabletrack.table_track_API.model.entity.product_import.Warehouse;
import com.tabletrack.table_track_API.repository.ContainerRepository;
import com.tabletrack.table_track_API.repository.WarehouseRepository;
import com.tabletrack.table_track_API.service.ContainerService;
import com.tabletrack.table_track_API.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final ContainerRepository containerRepository;
    private final ContainerService containerService;

    @Override
    public WarehouseResponse create(WarehouseRequest warehouseRequest) {
        if (warehouseRequest.getName() == null || warehouseRequest.getAddress() == null || warehouseRequest.getCountry() == null) {
            throw new IllegalArgumentException("Warehouse name, address, and country must be provided.");
        }

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
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id : %s", id)));

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
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id : %s", id)));

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
                .orElseThrow(() -> new NoSuchElementException(String.format("not found warehouse with id : %s", id)));

        warehouseRepository.delete(warehouse);
    }

    @Override
    public List<Container> listAllContainer(String warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NoSuchElementException(String.format("no such warehouse found with id : %s", warehouseId)));

        return containerRepository.findAll().stream()
                .filter(x -> x.getWarehouseId() != null && x.getWarehouseId().getId().equals(warehouse.getId()))
                .toList();
    }

    @Override
    public ContainerResponse findContainer(String warehouseId, String containerId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NoSuchElementException(String.format("no such warehouse found with id : %s", warehouseId)));

        Container lookContainer = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("no such container found with id : %s", containerId)));

        if (!lookContainer.getWarehouseId().getId().equals(warehouse.getId())) {
            throw new NoSuchElementException(String.format("container %s is located in a different warehouse",
                    lookContainer.getContainerCode()));
        }

        return ContainerResponse.builder()
                .containerCode(lookContainer.getContainerCode())
                .build();
    }

    @Override
    public ContainerResponse addContainer(String warehouseId, String containerId) {
        return containerService.moveToWarehouse(warehouseId, containerId);
    }
}
