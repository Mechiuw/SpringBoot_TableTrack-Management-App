package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.WarehouseRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.WarehouseResponse;
import com.tabletrack.table_track_API.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
