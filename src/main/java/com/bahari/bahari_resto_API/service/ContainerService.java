package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.ContainerRequest;
import com.bahari.bahari_resto_API.model.dto.response.ContainerResponse;
import com.bahari.bahari_resto_API.model.entity.Container;

import java.util.List;

public interface ContainerService {
    ContainerResponse create(ContainerRequest containerRequest);

    ContainerResponse getById(String id);

    List<Container> getAll();

    ContainerResponse update(String id,ContainerRequest containerRequest);

    void delete(String id);
}
