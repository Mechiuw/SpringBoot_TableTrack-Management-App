package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.ImportRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.ImportResponse;
import com.tabletrack.table_track_API.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPointApp.IMPORT)
@RequiredArgsConstructor
public class ImportController {
    private final ImportService importService;
    public ResponseEntity<?> create(ImportRequest importRequest){
        ImportResponse importResponse = importService.create(importRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully created import transaction")
                        .data(importResponse)
                        .build()
        );
    }

    public ResponseEntity<?> getById(String id){
        ImportResponse importResponse = importService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch data")
                        .data(importResponse)
                        .build()
        );
    }
    public ResponseEntity<?> getAll(){}
    public ResponseEntity<?> update(String id, ImportRequest importRequest){}
    public void delete(ImportRequest importRequest){}
}
