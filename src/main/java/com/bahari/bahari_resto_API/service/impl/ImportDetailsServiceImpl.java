package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ImportDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.ImportDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.ImportDetails;
import com.bahari.bahari_resto_API.repository.ImportDetailsRepository;
import com.bahari.bahari_resto_API.service.ImportDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportDetailsServiceImpl implements ImportDetailsService {
    private final ImportDetailsRepository importDetailsRepository;

    @Override
    public ImportDetailsResponse create(ImportDetailsRequest importDetailsRequest) {
        return null;
    }

    @Override
    public ImportDetailsResponse getById(String id) {
        return null;
    }

    @Override
    public List<ImportDetails> getAll() {
        return null;
    }

    @Override
    public ImportDetailsResponse update(String id, ImportDetailsRequest importDetailsRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
