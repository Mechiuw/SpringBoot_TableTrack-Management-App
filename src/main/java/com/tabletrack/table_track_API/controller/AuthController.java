package com.tabletrack.table_track_API.controller;

import com.tabletrack.table_track_API.constant.EndPointApp;
import com.tabletrack.table_track_API.model.dto.request.AuthRequest;
import com.tabletrack.table_track_API.model.dto.response.CommonResponse;
import com.tabletrack.table_track_API.model.dto.response.LoginResponse;
import com.tabletrack.table_track_API.model.dto.response.RegisterResponse;
import com.tabletrack.table_track_API.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPointApp.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(EndPointApp.REGISTER)
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest){
        RegisterResponse registerResponse = authService.registerCustomer(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Success Registered")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(EndPointApp.LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.login(authRequest);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Success login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
