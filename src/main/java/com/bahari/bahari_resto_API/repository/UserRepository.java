package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
