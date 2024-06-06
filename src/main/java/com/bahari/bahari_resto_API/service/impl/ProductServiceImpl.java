package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.model.dto.request.ProductRequest;
import com.bahari.bahari_resto_API.model.dto.response.ProductResponse;
import com.bahari.bahari_resto_API.model.entity.Product;
import com.bahari.bahari_resto_API.repository.ProductRepository;
import com.bahari.bahari_resto_API.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .distributionType(productRequest.getDistributionType())
                .productType(productRequest.getEProductType())
                .build();
        productRepository.save(product);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .distributionType(product.getDistributionType())
                .eProductType(product.getProductType())
                .build();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public ProductResponse getById(String id) {
        return null;
    }

    @Override
    public ProductResponse update(String id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
