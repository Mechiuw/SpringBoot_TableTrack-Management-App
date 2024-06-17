package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ImportRequest;
import com.bahari.bahari_resto_API.model.dto.response.ContainerResponse;
import com.bahari.bahari_resto_API.model.dto.response.ImportDetailsResponse;
import com.bahari.bahari_resto_API.model.dto.response.ImportResponse;
import com.bahari.bahari_resto_API.model.entity.*;
import com.bahari.bahari_resto_API.repository.*;
import com.bahari.bahari_resto_API.service.ImportService;
import com.bahari.bahari_resto_API.utils.ValidImportRequest;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Validated
@Transactional(rollbackOn = RollbackException.class)
public class ImportServiceImpl implements ImportService {
    private final ImportRepository importRepository;
    private final StoreRepository storeRepository;
    private final WarehouseRepository warehouseRepository;
    private final ContainerRepository containerRepository;

    @Override
    public void ImportData(@ValidImportRequest ImportRequest importRequest) {
        if (importRequest.getStoreId() == null) {
            throw new NoSuchElementException("Store ID is required");
        }
        if (importRequest.getWarehouseId() == null) {
            throw new NoSuchElementException("Warehouse ID is required");
        }
        if(importRequest.getShipment() == null){
            throw new NoSuchElementException("shipment is required");
        }
        if (importRequest.getContainerRequestList().stream().findAny().isEmpty()) {
            throw new NoSuchElementException("Containers are empty");
        }
        if (importRequest.getImportDetailsRequests().stream().findAny().isEmpty()) {
            throw new NoSuchElementException("Import details are empty");
        }
    }

    @Override
    public ImportResponse create(ImportRequest importRequest) {
        //validator - validation layer
        ImportData(importRequest);

        // entity build layer
        Store store = storeRepository.findById(importRequest.getStoreId())
                .orElseThrow(() -> new NoSuchElementException(String.format("no store found with id %s",importRequest.getStoreId())));

        Warehouse warehouse = warehouseRepository.findById(importRequest.getWarehouseId())
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id %s",importRequest.getWarehouseId())));

        List<Container> containers = containerRepository.findByWarehouseId(warehouse);
        List<ContainerResponse> containerResponses = containers.stream()
                .map(x -> ContainerResponse.builder()
                        .importId(x.getImportId().getId())
                        .warehouseId(x.getWarehouseId().getId())
                        .containerCode(x.getContainerCode())
                        .rawMaterialList(x.getRawMaterialList())
                        .build()).toList();

        if(containers.isEmpty()){
            throw new NoSuchElementException("No Containers found inside the exact warehouse");
        }

        Import imports = Import.builder()
                .store(store)
                .warehouse(warehouse)
                .containers(containers)
                .shipment(importRequest.getShipment())
                .build();

        List<ImportDetails> importDetails = importRequest.getImportDetailsRequests().stream()
                .map(importDetailsRequest -> ImportDetails.builder()
                        .Tax(importDetailsRequest.getTax())
                        .boarded(importDetailsRequest.getBoarded())
                        .arrival(importDetailsRequest.getArrival())
                        .importId(imports)
                        .build())
                .toList();
        imports.setImportDetails(importDetails);

        // saved transaction
        Import savedImport = importRepository.saveAndFlush(imports);

        // response layer #1
        List<ImportDetailsResponse> importDetailsResponses = importDetails.stream()
                .map(imp -> ImportDetailsResponse.builder()
                        .id(imp.getId())
                        .boarded(imp.getBoarded())
                        .arrival(imp.getArrival())
                        .tax(imp.getTax())
                        .importId(imp.getImportId().getId())
                        .build()).toList();

        // response layer #2
        return ImportResponse.builder()
                .id(savedImport.getId())
                .eShipment(savedImport.getShipment())
                .storeId(savedImport.getStore().getId())
                .warehouseId(savedImport.getWarehouse().getId())
                .importDetailsResponseList(importDetailsResponses)
                .containerResponseList(containerResponses)
                .build();
    }

    @Override
    public ImportResponse getById(String id) {
        Import imp = importRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found any import with id : %s",id)));
        List<Container> containerResponses = imp.getContainers();
        List<ContainerResponse> containerResponseList = containerResponses.stream()
                .map(ctx -> ContainerResponse.builder()
                        .id(ctx.getId())
                        .containerCode(ctx.getContainerCode())
                        .colorStatus(ctx.getStatus())
                        .rawMaterialList(ctx.getRawMaterialList())
                        .importId(ctx.getImportId().getId())
                        .build()).toList();

        List<ImportDetails> impDetails = imp.getImportDetails();
        List<ImportDetailsResponse> impDetailsResponses = impDetails.stream()
                .map(impd -> ImportDetailsResponse.builder()
                        .id(impd.getId())
                        .boarded(impd.getBoarded())
                        .arrival(impd.getArrival())
                        .tax(impd.getTax())
                        .importId(impd.getImportId().getId())
                        .build())
                .toList();
        return ImportResponse.builder()
                .id(imp.getId())
                .eShipment(imp.getShipment())
                .storeId(imp.getStore().getId())
                .warehouseId(imp.getWarehouse().getId())
                .importDetailsResponseList(impDetailsResponses)
                .containerResponseList(containerResponseList)
                .build();
    }

    @Override
    public List<Import> getAll() {
        return importRepository.findAll();
    }

    @Override
    public ImportResponse update(String id, ImportRequest importRequest) {
        ImportData(importRequest);
        Import imp = importRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found import with id : %s",id)));
        Store searchStore = storeRepository.findById(imp.getStore().getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("not found store with id : %s",imp.getStore().getId())));;
        Warehouse warehouse = warehouseRepository.findById(imp.getWarehouse().getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("not found warehouse with id : %s",imp.getWarehouse().getId())));
        List<Container> containers = containerRepository.findByWarehouseId(imp.getWarehouse());

        imp.setStore(searchStore);
        imp.setWarehouse(warehouse);
        imp.setShipment(importRequest.getShipment());
        imp.setContainers(containers);

        List<ImportDetails> importDetails = importRequest.getImportDetailsRequests().stream()
                .map(importDetailsRequest -> ImportDetails.builder()
                        .Tax(importDetailsRequest.getTax())
                        .boarded(importDetailsRequest.getBoarded())
                        .arrival(importDetailsRequest.getArrival())
                        .importId(imp)
                        .build())
                .toList();
        imp.setImportDetails(importDetails);

        Import savedImport = importRepository.saveAndFlush(imp);

        List<ImportDetailsResponse> importDetailsResponses = importDetails.stream()
                .map(details -> ImportDetailsResponse.builder()
                        .id(details.getId())
                        .boarded(details.getBoarded())
                        .arrival(details.getArrival())
                        .tax(details.getTax())
                        .importId(details.getImportId().getId())
                        .build())
                .toList();

        List<ContainerResponse> containerResponses = containers.stream()
                .map(cntrs -> ContainerResponse.builder()
                        .id(cntrs.getId())
                        .containerCode(cntrs.getContainerCode())
                        .colorStatus(cntrs.getStatus())
                        .importId(cntrs.getImportId().getId())
                        .warehouseId(cntrs.getWarehouseId().getId())
                        .rawMaterialList(cntrs.getRawMaterialList())
                        .build())
                .toList();
        return ImportResponse.builder()
                .id(savedImport.getId())
                .storeId(savedImport.getStore().getId())
                .warehouseId(savedImport.getWarehouse().getId())
                .eShipment(savedImport.getShipment())
                .importDetailsResponseList(importDetailsResponses)
                .containerResponseList(containerResponses)
                .build();
    }

    @Override
    public void delete(String id) {
        Import imp = importRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found any import with id : %s",id)));
        importRepository.delete(imp);
    }
}
