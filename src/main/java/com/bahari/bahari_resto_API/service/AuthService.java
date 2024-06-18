package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.dto.request.AuthRequest;
import com.bahari.bahari_resto_API.model.dto.response.LoginResponse;
import com.bahari.bahari_resto_API.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}
