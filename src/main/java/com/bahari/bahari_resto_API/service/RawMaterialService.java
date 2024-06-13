package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.RawMaterialRequest;
import com.bahari.bahari_resto_API.model.dto.response.RawMaterialResponse;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;

import java.util.List;

public interface RawMaterialService {
    RawMaterialResponse create(RawMaterialRequest rawMaterialRequest);

    RawMaterialResponse getById(String id);

    List<RawMaterial> getAll();

    RawMaterialResponse update(String id,RawMaterialRequest rawMaterialRequest);

    void delete(String id);
}
