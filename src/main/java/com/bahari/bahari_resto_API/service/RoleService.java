package com.bahari.bahari_resto_API.service;

import com.bahari.bahari_resto_API.constant.ERole;
import com.bahari.bahari_resto_API.model.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
