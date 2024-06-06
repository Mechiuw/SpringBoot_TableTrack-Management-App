package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.StoreRequest;
import com.bahari.bahari_resto_API.model.dto.response.StoreResponse;
import com.bahari.bahari_resto_API.model.entity.Store;
import com.bahari.bahari_resto_API.repository.CustomerRepository;
import com.bahari.bahari_resto_API.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final CustomerRepository customerRepository;

    @Override
    public StoreResponse create(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public List<Store> getAll() {
        return null;
    }

    @Override
    public StoreResponse getById(String id) {
        return null;
    }

    @Override
    public StoreResponse update(String id, StoreRequest storeRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
