package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.StoreRequest;
import com.tabletrack.table_track_API.model.dto.response.StoreResponse;
import com.tabletrack.table_track_API.model.entity.order.Store;

import java.util.*;

public interface StoreService {
    StoreResponse create(StoreRequest storeRequest);
    List<Store> getAll();
    StoreResponse getById(String id);
    StoreResponse update(String id,StoreRequest storeRequest);
    void delete(String id);

}
