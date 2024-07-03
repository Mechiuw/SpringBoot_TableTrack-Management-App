package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.ProductRequest;
import com.tabletrack.table_track_API.model.dto.response.ProductResponse;
import com.tabletrack.table_track_API.model.entity.order.Product;

import java.util.*;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    List<Product> getAll();
    ProductResponse getById(String id);
    ProductResponse update(String id, ProductRequest productRequest);
    void delete(String id);

    List<Product> suggestionProduct(String input);
}
