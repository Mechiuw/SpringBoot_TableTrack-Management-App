package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.ImportRequest;
import com.bahari.bahari_resto_API.model.dto.response.ImportResponse;
import com.bahari.bahari_resto_API.model.entity.Import;

import java.util.List;

public interface ImportService {
    ImportResponse create(ImportRequest importRequest);

    ImportResponse getById(String id);

    List<Import> getAll();

    ImportResponse update(String id, ImportRequest importRequest);

    void delete(String id);
}
