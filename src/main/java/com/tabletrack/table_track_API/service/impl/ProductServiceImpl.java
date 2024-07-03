package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.model.dto.request.ProductRequest;
import com.tabletrack.table_track_API.model.dto.response.ProductResponse;
import com.tabletrack.table_track_API.model.entity.order.Product;
import com.tabletrack.table_track_API.repository.ProductRepository;
import com.tabletrack.table_track_API.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return productRepository.findAll();
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .distributionType(product.getDistributionType())
                .eProductType(product.getProductType())
                .build();
    }

    @Override
    public ProductResponse update(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDistributionType(productRequest.getDistributionType());
        product.setProductType(productRequest.getEProductType());
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
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    @Override
    public List<Product> suggestionProduct(String input) {
        List<Product> collectProductData = productRepository.findAll();
        List<Product> suggestedProduct = new ArrayList<>();
        for(Product p : collectProductData){
            if(p != null){
                String productName = p.getName();
                if(productName != null && (productName.equalsIgnoreCase(input) || productName.toLowerCase().contains(input))){
                    suggestedProduct.add(p);
                }
            }
        }
        return suggestedProduct;
    }
}
