package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.RawMaterialRequest;
import com.tabletrack.table_track_API.model.dto.response.RawMaterialResponse;
import com.tabletrack.table_track_API.model.entity.product_import.RawMaterial;

import java.util.List;

public interface RawMaterialService {
    RawMaterialResponse create(RawMaterialRequest rawMaterialRequest);

    RawMaterialResponse getById(String id);

    List<RawMaterial> getAll();

    RawMaterialResponse update(String id,RawMaterialRequest rawMaterialRequest);

    void delete(String id);

    RawMaterialResponse moveToContainer(String materialId, String containerId);

    RawMaterialResponse moveFromContainer(String materialId);
}
