package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,String> {
}
