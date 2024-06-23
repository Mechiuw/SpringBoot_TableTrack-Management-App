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
    public ResponseEntity<?> createContainer(@RequestBody ContainerRequest containerRequest){
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
        ContainerResponse containerResponse = containerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            CommonResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Successfully fetch data")
                    .data(containerResponse)
                    .build()
        );
    }

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

}
