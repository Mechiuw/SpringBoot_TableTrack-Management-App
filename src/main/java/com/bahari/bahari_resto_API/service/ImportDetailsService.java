package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.ImportDetailsRequest;
import com.bahari.bahari_resto_API.model.dto.response.ImportDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.ImportDetails;

import java.util.List;

public interface ImportDetailsService {
    ImportDetailsResponse create(ImportDetailsRequest importDetailsRequest);
    ImportDetailsResponse getById(String id);
    List<ImportDetails> getAll();
    ImportDetailsResponse update(String id, ImportDetailsRequest importDetailsRequest);
    void delete(String id);
}
