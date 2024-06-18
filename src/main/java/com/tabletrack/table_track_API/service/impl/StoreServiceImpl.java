package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.model.dto.request.StoreRequest;
import com.tabletrack.table_track_API.model.dto.response.StoreResponse;
import com.tabletrack.table_track_API.model.entity.Store;
import com.tabletrack.table_track_API.repository.StoreRepository;
import com.tabletrack.table_track_API.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
                .id(store.getId())
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
        Store store = storeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Store not found with id : " + id));
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNum(store.getPhoneNumber())
                .build();
    }

    @Override
    public StoreResponse update(String id, StoreRequest storeRequest) {
        Store store = storeRepository.findById(id).orElseThrow();
        store.setName(storeRequest.getName());
        store.setAddress(storeRequest.getAddress());
        store.setEmail(storeRequest.getEmail());
        store.setPhoneNumber(storeRequest.getPhoneNumber());
        storeRepository.save(store);
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNum(store.getPhoneNumber())
                .build();
    }

    @Override
    public void delete(String id) {
        Store store = storeRepository.findById(id).orElseThrow();
        storeRepository.delete(store);
    }
}
