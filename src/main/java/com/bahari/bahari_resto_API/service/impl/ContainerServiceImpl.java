package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.exceptions.InvalidWarehouseDetachException;
import com.bahari.bahari_resto_API.exceptions.UnplacedContainerException;
import com.bahari.bahari_resto_API.model.dto.request.ContainerRequest;
import com.bahari.bahari_resto_API.model.dto.request.RawMaterialRequest;
import com.bahari.bahari_resto_API.model.dto.response.ContainerResponse;
import com.bahari.bahari_resto_API.model.entity.Container;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
import com.bahari.bahari_resto_API.repository.ContainerRepository;
import com.bahari.bahari_resto_API.repository.RawMaterialRepository;
import com.bahari.bahari_resto_API.repository.WarehouseRepository;
import com.bahari.bahari_resto_API.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final ContainerRepository containerRepository;
    private final WarehouseRepository warehouseRepository;
    private final RawMaterialRepository rawMaterialRepository;

    @Override
    public ContainerResponse create(ContainerRequest containerRequest) throws IllegalArgumentException, DataAccessException {
        // EMPTY CONTAINER
        //1. NO IMPORT ID
        //2. NO WAREHOUSE ID
        //3. DEFAULT STATUS
        //4. NO RAW MATERIAL
        if (containerRequest.getColorStatus() == null || containerRequest.getContainerCode() == null) {
            throw new IllegalArgumentException("Color status and container code must be provided.");
        }

        Container container = Container.builder()
                .status(containerRequest.getColorStatus())
                .warehouseId(containerRequest.getWarehouseId())
                .containerCode(containerRequest.getContainerCode())
                .build();
        Container unallocatedContainer = containerRepository.save(container);
        return ContainerResponse.builder()
                .id(unallocatedContainer.getId())
                .containerCode(unallocatedContainer.getContainerCode())
                .colorStatus(unallocatedContainer.getStatus())
                .importId(unallocatedContainer.getImportId().getId())
                .warehouseId(unallocatedContainer.getWarehouseId().getId())
                .build();
    }

    @Override
    public ContainerResponse getById(String id) {
        Container container = containerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("no such container with id : %s",id)));
        return ContainerResponse.builder()
                .id(container.getId())
                .containerCode(container.getContainerCode())
                .colorStatus(container.getStatus())
                .importId(container.getImportId().getId())
                .warehouseId(container.getWarehouseId().getId())
                .build();
    }

    @Override
    public List<Container> getAll() {
        return containerRepository.findAll();
    }

    @Override
    public ContainerResponse update(String id, ContainerRequest containerRequest) {
        Container container = containerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("no container with id : %s",id)));
        container.setContainerCode(containerRequest.getContainerCode());
        container.setContainerCode(String.valueOf(containerRequest.getColorStatus()));
        container.setContainerCode(containerRequest.getImportId().getId());
        container.setContainerCode(containerRequest.getWarehouseId().getId());
        container.setContainerCode(containerRequest.getContainerCode());

        Container savedContainer = containerRepository.saveAndFlush(container);

        return ContainerResponse.builder()
                .id(savedContainer.getId())
                .containerCode(savedContainer.getContainerCode())
                .colorStatus(savedContainer.getStatus())
                .importId(savedContainer.getImportId().getId())
                .warehouseId(savedContainer.getWarehouseId().getId())
                .build();
    }

    @Override
    public void delete(String id) {
        Container container = containerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("cannot find any related containers with id : %s",id)));
        containerRepository.delete(container);
    }

    @Override
    public ContainerResponse addRawMaterials(String containerId, ContainerRequest containerRequest) {
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found container with id : %s",containerId)));

        if(container.getWarehouseId() != null || !container.getWarehouseId().equals("UNPLACED")) {
            List<RawMaterial> rawMaterialList = new ArrayList<>();
            for(RawMaterialRequest rawMaterialRequest : containerRequest.getRequestList()){
                RawMaterial rawMaterial = RawMaterial.builder()
                        .name(rawMaterialRequest.getName())
                        .expDate(rawMaterialRequest.getExpDate())
                        .price(rawMaterialRequest.getPrice())
                        .manufacture(rawMaterialRequest.getManufacture())
                        .stocks(rawMaterialRequest.getStocks())
                        .distributionType(rawMaterialRequest.getDistributionType())
                        .container(container)
                        .build();
                rawMaterialList.add(rawMaterial);
            }
            container.setRawMaterialList(rawMaterialList);
            Container filledContainer = containerRepository.saveAndFlush(container);

            return ContainerResponse.builder()
                    .id(filledContainer.getId())
                    .containerCode(filledContainer.getContainerCode())
                    .colorStatus(filledContainer.getStatus())
                    .warehouseId(filledContainer.getWarehouseId().getId())
                    .rawMaterialList(filledContainer.getRawMaterialList())
                    .build();
        } else {
            throw new UnplacedContainerException(String.format("container either unplaced or not inside warehouse yet with id : %s",containerId));
        }

    }

    public ContainerResponse moveToWarehouse(String warehouseId,String containerId){
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found container with id : %s",containerId)));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found warehouse with id : %s",warehouseId)));

        container.setWarehouseId(warehouse);
        Container containerInWarehouse = containerRepository.save(container);
        return ContainerResponse.builder()
                .id(containerInWarehouse.getId())
                .containerCode(containerInWarehouse.getContainerCode())
                .colorStatus(containerInWarehouse.getStatus())
                .warehouseId(containerInWarehouse.getWarehouseId().getId())
                .build();
    }

    @Override
    public ContainerResponse detachFromWarehouse(String containerId) {
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found container with it : %s",containerId)));
        if(container.getWarehouseId().equals("DETACH") || container.getWarehouseId().equals(null)){
            throw new InvalidWarehouseDetachException(String.format("Cannot detach container when it's already detached from the warehouse with id : %s",containerId));
        }
        container.setWarehouseId(null);
        return ContainerResponse.builder()
                .id(container.getId())
                .containerCode(container.getId())
                .build();
    }


}
