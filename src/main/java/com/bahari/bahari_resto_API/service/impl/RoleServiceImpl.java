package com.bahari.bahari_resto_API.service.impl;

import com.bahari.bahari_resto_API.constant.ERole;
import com.bahari.bahari_resto_API.model.entity.Role;
import com.bahari.bahari_resto_API.repository.RoleRepository;
import com.bahari.bahari_resto_API.service.RoleService;
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
