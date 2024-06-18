package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {
    Optional<UserCredential> findByUsername(String username);
}
