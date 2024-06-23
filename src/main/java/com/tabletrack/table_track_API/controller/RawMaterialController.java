package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.RawMaterialRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.RawMaterialResponse;
import com.tabletrack.table_track_API.model.entity.product_import.RawMaterial;
import com.tabletrack.table_track_API.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EndPointApp.RAW_MATERIAL)
@RequiredArgsConstructor
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;
    public ResponseEntity<?> create(RawMaterialRequest rawMaterialRequest){
        RawMaterialResponse rawMaterial = rawMaterialService.create(rawMaterialRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created raw material")
                        .data(rawMaterial)
                        .build()
        );
    }

    public ResponseEntity<?> getById(String id){}
    public ResponseEntity<?> getAll(){
        List<RawMaterial> list = rawMaterialService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully list all")
                        .data(list)
                        .build()
        );
    }
    public ResponseEntity<?> update(String id,RawMaterialRequest request){
        RawMaterialResponse rawMaterial = rawMaterialService.update(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated raw material")
                        .data(rawMaterial)
                        .build()
        );
    }
    public void delete(String id){
        rawMaterialService.delete(id);
        ResponseEntity.ok();
    }
}
