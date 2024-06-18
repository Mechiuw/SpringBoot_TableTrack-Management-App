package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.ImportRequest;
import com.tabletrack.table_track_API.model.dto.response.ImportResponse;
import com.tabletrack.table_track_API.model.entity.Import;

import java.util.List;

public interface ImportService {
    void ImportData(ImportRequest importRequest);
    ImportResponse create(ImportRequest importRequest);

    ImportResponse getById(String id);

    List<Import> getAll();

    ImportResponse update(String id, ImportRequest importRequest);

    void delete(String id);
}
