package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.constant.ERole;
import com.tabletrack.table_track_API.model.entity.authentication.Role;
import com.tabletrack.table_track_API.repository.RoleRepository;
import com.tabletrack.table_track_API.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if(optionalRole.isPresent()){
            return optionalRole.get();
        }
        Role currentRole = Role.builder()
                .name(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}
