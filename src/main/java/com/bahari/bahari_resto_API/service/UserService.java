package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
