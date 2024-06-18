package com.tabletrack.table_track_API.service;

import com.tabletrack.table_track_API.constant.ERole;
import com.tabletrack.table_track_API.model.entity.authentication.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
