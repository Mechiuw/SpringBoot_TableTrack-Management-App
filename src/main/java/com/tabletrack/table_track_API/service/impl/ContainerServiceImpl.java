package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.exceptions.InvalidWarehouseDetachException;
import com.tabletrack.table_track_API.exceptions.UnplacedContainerException;
import com.tabletrack.table_track_API.model.dto.request.ContainerRequest;
import com.tabletrack.table_track_API.model.dto.request.RawMaterialRequest;
import com.tabletrack.table_track_API.model.dto.response.ContainerResponse;
import com.tabletrack.table_track_API.model.entity.Container;
import com.tabletrack.table_track_API.model.entity.RawMaterial;
import com.tabletrack.table_track_API.model.entity.Warehouse;
import com.tabletrack.table_track_API.repository.ContainerRepository;
import com.tabletrack.table_track_API.repository.RawMaterialRepository;
import com.tabletrack.table_track_API.repository.WarehouseRepository;
import com.tabletrack.table_track_API.service.ContainerService;
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
                .containerCode(containerRequest.getContainerCode())
                .status(containerRequest.getColorStatus())
                .build();
        Container unallocatedContainer = containerRepository.save(container);
        return ContainerResponse.builder()
                .id(unallocatedContainer.getId())
                .containerCode(unallocatedContainer.getContainerCode())
                .colorStatus(unallocatedContainer.getStatus())
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
        container.setStatus(containerRequest.getColorStatus());
        if (containerRequest.getImportId() != null) {
            container.setImportId(containerRequest.getImportId());
        }
        if (containerRequest.getWarehouseId() != null) {
            container.setWarehouseId(containerRequest.getWarehouseId());
        }

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

        if(container.getWarehouseId() == null || "UNPLACED".equals(container.getWarehouseId().getId())) {
            throw new UnplacedContainerException(String.format("container either unplaced or not inside warehouse yet with id : %s",containerId));
        }

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
                .importId(filledContainer.getImportId() != null ? filledContainer.getImportId().getId() : null)
                .rawMaterialList(filledContainer.getRawMaterialList())
                .build();
    }

    public ContainerResponse moveToWarehouse(String warehouseId,String containerId){
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found container with id : %s",containerId)));
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found warehouse with id : %s",warehouseId)));

        container.setWarehouseId(warehouse);
        Container containerInWarehouse = containerRepository.saveAndFlush(container);
        return ContainerResponse.builder()
                .id(containerInWarehouse.getId())
                .containerCode(containerInWarehouse.getContainerCode())
                .colorStatus(containerInWarehouse.getStatus())
                .warehouseId(containerInWarehouse.getWarehouseId().getId())
                .importId(containerInWarehouse.getImportId() != null ? containerInWarehouse.getImportId().getId() : null)
                .build();
    }

    @Override
    public ContainerResponse detachFromWarehouse(String containerId) {
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found container with it : %s",containerId)));

        if("DETACH".equals(container.getWarehouseId().getId()) || container.getWarehouseId() == null){
            throw new InvalidWarehouseDetachException(String.format("Cannot detach container when it's already detached from the warehouse with id : %s",containerId));
        }
        if(container.getImportId() != null && !container.getImportId().getId().isEmpty()){
            throw new InvalidWarehouseDetachException(String.format("Cannot detach container if container gets imported with import id : %s",container.getImportId().getId()));
        }

        container.setWarehouseId(null);
        Container detachedContainer = containerRepository.saveAndFlush(container);
        return ContainerResponse.builder()
                .id(detachedContainer.getId())
                .containerCode(detachedContainer.getContainerCode())
                .colorStatus(detachedContainer.getStatus())
                .importId(detachedContainer.getImportId() != null ? detachedContainer.getImportId().getId() : null)
                .build();
    }


}
