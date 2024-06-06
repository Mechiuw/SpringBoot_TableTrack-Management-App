package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.ProductRequest;
import com.bahari.bahari_resto_API.model.dto.response.ProductResponse;
import com.bahari.bahari_resto_API.model.entity.Product;

import java.util.*;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    List<Product> getAll();
    ProductResponse getById(String id);
    ProductResponse update(String id, ProductRequest productRequest);
    void delete(String id);
}
