package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ImportRequest;
import com.bahari.bahari_resto_API.model.dto.response.ImportResponse;
import com.bahari.bahari_resto_API.model.entity.Import;
import com.bahari.bahari_resto_API.model.entity.Store;
import com.bahari.bahari_resto_API.repository.ImportRepository;
import com.bahari.bahari_resto_API.repository.StoreRepository;
import com.bahari.bahari_resto_API.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    private final ImportRepository importRepository;
    private final StoreRepository storeRepository;

    @Override
    public ImportResponse create(ImportRequest importRequest) {
        Store store = storeRepository.findById(importRequest.getStoreId())
                .orElseThrow(() -> new NoSuchElementException(String.format("no store found with id %s",importRequest.getStoreId())));


        return null;
    }

    @Override
    public ImportResponse getById(String id) {
        return null;
    }

    @Override
    public List<Import> getAll() {
        return null;
    }

    @Override
    public ImportResponse update(String id, ImportRequest importRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
