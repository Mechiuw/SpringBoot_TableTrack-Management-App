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
import java.util.Optional;

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
        return storeRepository.findAll();
    }

    @Override
    public StoreResponse getById(String id) {
        Store store = storeRepository.findById(id).orElse(null);
        assert store != null;
        return StoreResponse.builder()
                .name(store.getName())
                .address(store.getAddress())
                .phoneNum(store.getPhoneNumber())
                .build();
    }

    @Override
    public StoreResponse update(String id, StoreRequest storeRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
