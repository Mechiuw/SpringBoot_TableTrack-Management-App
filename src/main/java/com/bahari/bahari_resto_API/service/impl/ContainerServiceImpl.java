package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ContainerRequest;
import com.bahari.bahari_resto_API.model.dto.response.ContainerResponse;
import com.bahari.bahari_resto_API.model.entity.Container;
import com.bahari.bahari_resto_API.repository.ContainerRepository;
import com.bahari.bahari_resto_API.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final ContainerRepository containerRepository;

    @Override
    public ContainerResponse create(ContainerRequest containerRequest) {
        return null;
    }

    @Override
    public ContainerResponse getById(String id) {
        return null;
    }

    @Override
    public List<Container> getAll() {
        return null;
    }

    @Override
    public ContainerResponse update(String id, ContainerRequest containerRequest) {
        return null;
    }

    @Override
    public void delete(ContainerRequest containerRequest) {

    }
}
