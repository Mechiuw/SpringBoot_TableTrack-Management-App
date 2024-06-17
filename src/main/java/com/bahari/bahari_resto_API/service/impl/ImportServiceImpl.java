package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ContainerRequest;
import com.bahari.bahari_resto_API.model.dto.request.ImportDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.request.ImportRequest;
import com.bahari.bahari_resto_API.model.dto.response.ImportDetailsResponse;
import com.bahari.bahari_resto_API.model.dto.response.ImportResponse;
import com.bahari.bahari_resto_API.model.entity.*;
import com.bahari.bahari_resto_API.repository.*;
import com.bahari.bahari_resto_API.service.ImportService;
import com.bahari.bahari_resto_API.utils.ValidImportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Validated
public class ImportServiceImpl implements ImportService {
    private final ImportRepository importRepository;
    private final StoreRepository storeRepository;
    private final WarehouseRepository warehouseRepository;
    private final ContainerRepository containerRepository;
    private final ImportDetailsRepository importDetailsRepository;

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
        ImportData(importRequest);

        Store store = storeRepository.findById(importRequest.getStoreId())
                .orElseThrow(() -> new NoSuchElementException(String.format("no store found with id %s",importRequest.getStoreId())));

        Warehouse warehouse = warehouseRepository.findById(importRequest.getWarehouseId())
                .orElseThrow(() -> new NoSuchElementException(String.format("no warehouse found with id %s",importRequest.getWarehouseId())));

        List<Container> containers = containerRepository.findAll().stream()
                .filter(x -> x.getWarehouseId().getId().equals(warehouse.getId())).toList();

        Import imports = Import.builder()
                .store(store)
                .warehouse(warehouse)
                .containers(containers)
                .shipment(importRequest.getShipment())
                .build();

        List<ImportDetails> importDetails = new ArrayList<>();
        for(ImportDetailsRequest importDetailsRequest : importRequest.getImportDetailsRequests()){
            ImportDetails importDetailsList = ImportDetails.builder()
                    .Tax(importDetailsRequest.getTax())
                    .boarded(importDetailsRequest.getBoarded())
                    .arrival(importDetailsRequest.getArrival())
                    .importId(imports)
                    .build();
            importDetails.add(importDetailsList);
        }
        imports.setImportDetails(importDetails);

        Import savedImport = importRepository.saveAndFlush(imports);

        List<ImportDetailsResponse> importDetailsResponses = importDetails.stream()
                .map(imp -> ImportDetailsResponse.builder()
                        .id(imp.getId())
                        .boarded(imp.getBoarded())
                        .arrival(imp.getArrival())
                        .tax(imp.getTax())
                        .importId(imp.getImportId().getId())
                        .build()).toList();

        return ImportResponse.builder()
                .id(savedImport.getId())
                .eShipment(savedImport.getShipment())
                .storeId(savedImport.getStore().getId())
                .warehouseId(savedImport.getWarehouse().getId())
                .importDetailsResponseList(importDetailsResponses)
                .build();
    }

    @Override
    public ImportResponse getById(String id) {
        return null;
    }

    @Override
    public List<Import> getAll() {
        return null;
    }

    @Override
    public ImportResponse update(String id, ImportRequest importRequest) {
        ImportData(importRequest);
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
