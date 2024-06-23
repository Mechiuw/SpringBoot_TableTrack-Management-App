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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPointApp.RAW_MATERIAL)
@RequiredArgsConstructor
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RawMaterialRequest rawMaterialRequest){
        RawMaterialResponse rawMaterial = rawMaterialService.create(rawMaterialRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created raw material")
                        .data(rawMaterial)
                        .build()
        );
    }

    @GetMapping(EndPointApp.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){
        RawMaterialResponse response = rawMaterialService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
          CommonResponse.builder()
                  .statusCode(HttpStatus.OK.value())
                  .message("Successfully fetch data")
                  .data(response)
                  .build()
        );
    }

    @GetMapping(EndPointApp.GET_ALL)
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
    @PutMapping(EndPointApp.PUT_BY_ID)
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody RawMaterialRequest request){
        RawMaterialResponse rawMaterial = rawMaterialService.update(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated raw material")
                        .data(rawMaterial)
                        .build()
        );
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    public void delete(@PathVariable String id){
        rawMaterialService.delete(id);
        ResponseEntity.ok();
    }

    @PutMapping("/{materialId}/{containerId}")
    public ResponseEntity<?> moveToContainer(@PathVariable String materialId, @PathVariable String containerId){
        RawMaterialResponse response = rawMaterialService.moveToContainer(materialId,containerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully moved raw material: "+ materialId + " | to container: " + containerId)
                        .data(response)
                        .build()
        );
    }
}
