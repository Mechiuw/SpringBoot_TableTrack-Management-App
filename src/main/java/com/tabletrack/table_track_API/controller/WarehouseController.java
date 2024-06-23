package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.WarehouseRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.WarehouseResponse;
import com.tabletrack.table_track_API.model.entity.product_import.Warehouse;
import com.tabletrack.table_track_API.service.WarehouseService;
import jakarta.activation.CommandMap;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPointApp.WAREHOUSE)
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WarehouseRequest warehouseRequest){
        WarehouseResponse warehouseResponse = warehouseService.create(warehouseRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Successfully created warehouse with id : " + warehouseResponse.getId())
                                .data(warehouseResponse)
                                .build()
                );
    }

    @GetMapping(EndPointApp.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){
        WarehouseResponse warehouseResponse = warehouseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch data")
                        .data(warehouseResponse)
                        .build()
        );
    }

    @GetMapping(EndPointApp.GET_ALL)
    public ResponseEntity<?> getAll(){
        List<Warehouse> warehouseList = warehouseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch all data")
                        .data(warehouseList)
                        .build()
        );
    }
    @PutMapping(EndPointApp.PUT_BY_ID)
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody WarehouseRequest warehouseRequest){
        WarehouseResponse warehouseResponse = warehouseService.update(id,warehouseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated data with id : " + warehouseResponse.getId())
                        .data(warehouseResponse)
                        .build()
        );
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    public ResponseEntity<?> delete(@PathVariable String id){
        warehouseService.delete(id);
        ResponseEntity.ok();
    }
}
