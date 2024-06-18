package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
