package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.StoreRequest;
import com.bahari.bahari_resto_API.model.dto.response.StoreResponse;
import com.bahari.bahari_resto_API.model.entity.Store;
import java.util.*;

public interface StoreService {
    StoreResponse create(StoreRequest storeRequest);
    List<Store> getAll();
    StoreResponse getById(String id);
    StoreResponse update(String id,StoreRequest storeRequest);
    void delete(String id);

}
