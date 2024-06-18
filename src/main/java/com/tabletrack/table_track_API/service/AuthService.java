package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.dto.request.AuthRequest;
import com.tabletrack.table_track_API.model.dto.response.LoginResponse;
import com.tabletrack.table_track_API.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}
