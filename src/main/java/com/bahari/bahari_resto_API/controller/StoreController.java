package com.bahari.bahari_resto_API.controller;

import com.bahari.bahari_resto_API.constant.EndPointApp;
import com.bahari.bahari_resto_API.model.dto.request.StoreRequest;
import com.bahari.bahari_resto_API.model.dto.response.CommonResponse;
import com.bahari.bahari_resto_API.model.dto.response.StoreResponse;
import com.bahari.bahari_resto_API.model.entity.Store;
import com.bahari.bahari_resto_API.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointApp.STORE)
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StoreRequest storeRequest){
        StoreResponse storeResponse = storeService.create(storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created store")
                        .data(storeResponse)
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Store> storeResponseList = storeService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message("Successfully fetch all data")
                        .data(storeResponseList)
                        .build());
    }


    @GetMapping(EndPointApp.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){
        StoreResponse storeResponse = storeService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message(String.format("Successfully fetch data with id : %s",id))
                        .data(storeResponse)
                        .build());
    }

    @PutMapping(EndPointApp.PUT_BY_ID)
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody StoreRequest storeRequest){
        StoreResponse storeResponse = storeService.update(id, storeRequest);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message(String.format("Successfully updated data with id : %s",id))
                        .data(storeResponse)
                        .build());
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    public void delete(@PathVariable String id){
        storeService.delete(id);
        ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully deleted ")
                        .build()
        );
    }
}
