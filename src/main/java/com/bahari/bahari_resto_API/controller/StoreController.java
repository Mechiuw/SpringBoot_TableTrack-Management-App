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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPointApp.STORE)
public class StoreController {
    private final StoreService storeService;

    public ResponseEntity<?> create(StoreRequest storeRequest){
        StoreResponse storeResponse = storeService.create(storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created store")
                        .data(storeResponse)
                        .build());
    }

    public ResponseEntity<?> getAll(){
        List<Store> storeResponseList = storeService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message("Successfully fetch all data")
                        .data(storeResponseList)
                        .build());
    }


    public ResponseEntity<?> getById(String id){
        StoreResponse storeResponse = storeService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message(String.format("Successfully fetch data with id : %s",id))
                        .data(storeResponse)
                        .build());
    }

    public ResponseEntity<?> update(String id, StoreRequest storeRequest){
        StoreResponse storeResponse = storeService.update(id, storeRequest);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message(String.format("Successfully updated data with id : %s",id))
                        .data(storeResponse)
                        .build());
    }

    public void delete(String id){
        storeService.delete(id);
        ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully deleted ")
                        .build()
        );
    }
}
