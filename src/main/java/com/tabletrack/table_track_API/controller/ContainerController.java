package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.ContainerRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.ContainerResponse;
import com.tabletrack.table_track_API.model.entity.product_import.Container;
import com.tabletrack.table_track_API.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPointApp.CONTAINER)
@RequiredArgsConstructor
public class ContainerController {
    private final ContainerService containerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ContainerRequest containerRequest){
        ContainerResponse containerResponse = containerService.create(containerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created container")
                        .data(containerResponse)
                        .build());
    }

    @GetMapping(EndPointApp.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){
        assert id != null : "container id forbidden to be null";
        ContainerResponse containerResponse = containerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            CommonResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Successfully fetch data")
                    .data(containerResponse)
                    .build()
        );
    }

    @GetMapping(EndPointApp.GET_ALL)
    public ResponseEntity<?> getAll(){
        List<Container> list = containerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch all data")
                        .data(list)
                        .build()
        );
    }

    @PutMapping(EndPointApp.PUT_BY_ID)
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody ContainerRequest containerRequest){
        assert id != null : "container id forbidden to be null";
        ContainerResponse containerResponse = containerService.update(id,containerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updates data")
                        .data(containerResponse)
                        .build()
        );
    }

    @DeleteMapping(EndPointApp.DELETE_BY_ID)
    public void delete(@PathVariable String id){
        assert id != null : "container id forbidden to be null";
        containerService.delete(id);
        ResponseEntity.ok();
    }

    @PutMapping(EndPointApp.FILL_BY_ID)
    public ResponseEntity<?> fillContainer(@PathVariable String id,@RequestBody ContainerRequest containerRequest){
        assert id != null : "container id forbidden to be null";

        ContainerResponse fill = containerService.addRawMaterials(id, containerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully filled container with id: " + id)
                        .data(fill)
                        .build()
        );
    }

    @PutMapping("/{warehouseId}/containers/{containerId}")
    public ResponseEntity<?> moveToWarehouse(@PathVariable String warehouseId, @PathVariable String containerId){
        assert warehouseId != null : "warehouse id forbidden to be null";
        assert containerId != null : "container id forbidden to be null";

        ContainerResponse move = containerService.moveToWarehouse(warehouseId, containerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully moved container " + containerId + " to warehouse " + warehouseId)
                        .data(move)
                        .build()
        );
    }

    @PutMapping(EndPointApp.DETACH_BY_ID)
    public ResponseEntity<?> detachFromWarehouse(@PathVariable String id){
        assert id != null : "container id forbidden to be null";
        ContainerResponse detach = containerService.detachFromWarehouse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully detached Container from warehouse")
                        .data(detach)
                        .build()
        );
    }


}
