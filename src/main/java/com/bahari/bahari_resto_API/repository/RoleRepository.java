package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.constant.ERole;
import com.bahari.bahari_resto_API.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
