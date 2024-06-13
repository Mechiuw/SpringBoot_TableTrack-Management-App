package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.RawMaterialRequest;
import com.bahari.bahari_resto_API.model.dto.response.RawMaterialResponse;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;
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
        return null;
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
