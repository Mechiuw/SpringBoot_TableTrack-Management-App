package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.RawMaterialRequest;
import com.bahari.bahari_resto_API.model.dto.response.RawMaterialResponse;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;
import com.bahari.bahari_resto_API.repository.ContainerRepository;
import com.bahari.bahari_resto_API.repository.RawMaterialRepository;
import com.bahari.bahari_resto_API.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {
    private final RawMaterialRepository rawMaterialRepository;

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
        return null;
    }

    @Override
    public List<RawMaterial> getAll() {
        return null;
    }

    @Override
    public RawMaterialResponse update(String id, RawMaterialRequest rawMaterialRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
