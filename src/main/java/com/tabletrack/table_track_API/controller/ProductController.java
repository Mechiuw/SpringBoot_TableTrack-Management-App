package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.ProductRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.ProductResponse;
import com.tabletrack.table_track_API.model.entity.order.Product;
import com.tabletrack.table_track_API.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointApp.PRODUCT)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created Product")
                        .data(productResponse)
                        .build());
    }

    @GetMapping(EndPointApp.GET_ALL)
    public ResponseEntity<?> getAll(){
        List<Product> productList = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch all data")
                        .data(productList)
                        .build());
    }

    @GetMapping(EndPointApp.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){
        ProductResponse productResponse = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(String.format("Successfully fetch data with id : %s",id))
                        .data(productResponse)
                        .build());
    }

    @PutMapping(EndPointApp.PUT_BY_ID)
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.update(id,productRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(String.format("Successfully Updated data with id : %s",id))
                        .data(productResponse)
                        .build());
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    public void delete(@PathVariable String id){
        productService.delete(id);
        ResponseEntity.ok();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "input")String input){
        List<Product> productList = productService.suggestionProduct(input);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("successfully fetched")
                        .data(productList)
                        .build()
        );
    }
}
