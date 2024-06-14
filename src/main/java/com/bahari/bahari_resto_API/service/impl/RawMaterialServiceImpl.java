package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.RawMaterialRequest;
import com.bahari.bahari_resto_API.model.dto.response.RawMaterialResponse;
import com.bahari.bahari_resto_API.model.entity.Container;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;
import com.bahari.bahari_resto_API.repository.ContainerRepository;
import com.bahari.bahari_resto_API.repository.RawMaterialRepository;
import com.bahari.bahari_resto_API.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {
    private final RawMaterialRepository rawMaterialRepository;
    private final ContainerRepository containerRepository;

    @Override
    public RawMaterialResponse create(RawMaterialRequest rawMaterialRequest) {
        RawMaterial rawMaterial = RawMaterial.builder()
                .name(rawMaterialRequest.getName())
                .expDate(rawMaterialRequest.getExpDate())
                .price(rawMaterialRequest.getPrice())
                .manufacture(rawMaterialRequest.getManufacture())
                .stocks(rawMaterialRequest.getStocks())
                .distributionType(rawMaterialRequest.getDistributionType())
                .build();
        rawMaterialRepository.save(rawMaterial);
        return RawMaterialResponse.builder()
                .name(rawMaterial.getName())
                .expDate(rawMaterial.getExpDate())
                .price(rawMaterial.getPrice())
                .manufacture(rawMaterial.getManufacture())
                .stocks(rawMaterial.getStocks())
                .distributionType(rawMaterial.getDistributionType())
                .build();
    }

    @Override
    public RawMaterialResponse getById(String id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(String.format("not found any raw material with id : %s",id)));
        return RawMaterialResponse.builder()
                .name(rawMaterial.getName())
                .expDate(rawMaterial.getExpDate())
                .price(rawMaterial.getPrice())
                .manufacture(rawMaterial.getManufacture())
                .stocks(rawMaterial.getStocks())
                .distributionType(rawMaterial.getDistributionType())
                .build();
    }

    @Override
    public List<RawMaterial> getAll() {
        return rawMaterialRepository.findAll();
    }

    @Override
    public RawMaterialResponse update(String id, RawMaterialRequest rawMaterialRequest) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("no such raw material found with id : %s",id)));
        rawMaterial.setName(rawMaterialRequest.getName());
        rawMaterial.setExpDate(rawMaterialRequest.getExpDate());
        rawMaterial.setPrice(rawMaterialRequest.getPrice());
        rawMaterial.setManufacture(rawMaterialRequest.getManufacture());
        rawMaterial.setStocks(rawMaterialRequest.getStocks());
        rawMaterial.setDistributionType(rawMaterialRequest.getDistributionType());

        RawMaterial updatedRawMaterial = rawMaterialRepository.save(rawMaterial);

        return RawMaterialResponse.builder()
                .id(updatedRawMaterial.getId())
                .name(updatedRawMaterial.getName())
                .expDate(updatedRawMaterial.getExpDate())
                .price(updatedRawMaterial.getPrice())
                .manufacture(updatedRawMaterial.getManufacture())
                .stocks(updatedRawMaterial.getStocks())
                .distributionType(updatedRawMaterial.getDistributionType())
                .build();
    }

    @Override
    public void delete(String id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found any raw material to delete with id : %s",id)));
        rawMaterialRepository.delete(rawMaterial);
    }

    @Override
    public RawMaterialResponse moveToContainer(String materialId, String containerId) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(materialId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found material with id : %s",materialId)));

        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException(String.format("no container found with id : %s",containerId)));
        rawMaterial.setContainer(container);
        RawMaterial updateContainerToRawMaterial = rawMaterialRepository.save(rawMaterial);
        return RawMaterialResponse.builder()
                .name(updateContainerToRawMaterial.getName())
                .expDate(updateContainerToRawMaterial.getExpDate())
                .price(updateContainerToRawMaterial.getPrice())
                .manufacture(updateContainerToRawMaterial.getManufacture())
                .stocks(updateContainerToRawMaterial.getStocks())
                .distributionType(updateContainerToRawMaterial.getDistributionType())
                .containerId(updateContainerToRawMaterial.getContainer().getId())
                .build();
    }

    @Override
    public RawMaterialResponse moveFromContainer(String materialId) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(materialId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found material with id : %s",materialId)));

        rawMaterial.setContainer(null);
        RawMaterial savedRawMaterial = rawMaterialRepository.save(rawMaterial);
        return RawMaterialResponse.builder()
                .name(savedRawMaterial.getName())
                .expDate(savedRawMaterial.getExpDate())
                .price(savedRawMaterial.getPrice())
                .manufacture(savedRawMaterial.getManufacture())
                .stocks(savedRawMaterial.getStocks())
                .distributionType(savedRawMaterial.getDistributionType())
                .containerId(savedRawMaterial.getContainer().getId())
                .build();
    }
}
