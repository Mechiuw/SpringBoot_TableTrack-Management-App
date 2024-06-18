package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.ImportDetailsRequest;
import com.tabletrack.table_track_API.model.dto.response.ImportDetailsResponse;
import com.tabletrack.table_track_API.model.entity.product_import.ImportDetails;

import java.util.List;

public interface ImportDetailsService {
    ImportDetailsResponse create(ImportDetailsRequest importDetailsRequest);
    ImportDetailsResponse getById(String id);
    List<ImportDetails> getAll();
    ImportDetailsResponse update(String id, ImportDetailsRequest importDetailsRequest);
    void delete(String id);
}
