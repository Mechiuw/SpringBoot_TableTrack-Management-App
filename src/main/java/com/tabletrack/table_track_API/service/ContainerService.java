package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.ContainerRequest;
import com.tabletrack.table_track_API.model.dto.response.ContainerResponse;
import com.tabletrack.table_track_API.model.entity.Container;

import java.util.List;

public interface ContainerService {
    ContainerResponse create(ContainerRequest containerRequest);

    ContainerResponse getById(String id);

    List<Container> getAll();

    ContainerResponse update(String id,ContainerRequest containerRequest);

    void delete(String id);

    ContainerResponse addRawMaterials(String containerId,ContainerRequest containerRequest);
    ContainerResponse moveToWarehouse(String warehouseId,String containerId);
    ContainerResponse detachFromWarehouse(String containerId);
}
