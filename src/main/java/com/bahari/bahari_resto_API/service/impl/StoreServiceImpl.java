package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.StoreRequest;
import com.bahari.bahari_resto_API.model.dto.response.StoreResponse;
import com.bahari.bahari_resto_API.model.entity.Store;
import com.bahari.bahari_resto_API.repository.CustomerRepository;
import com.bahari.bahari_resto_API.repository.StoreRepository;
import com.bahari.bahari_resto_API.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public StoreResponse create(StoreRequest storeRequest) {
        Store store = Store.builder()
                .name(storeRequest.getName())
                .address(storeRequest.getAddress())
                .email(storeRequest.getEmail())
                .phoneNumber(storeRequest.getPhoneNumber())
                .build();
        storeRepository.save(store);
        return StoreResponse.builder()
                .name(store.getName())
                .address(store.getAddress())
                .phoneNum(store.getPhoneNumber())
                .build();
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
