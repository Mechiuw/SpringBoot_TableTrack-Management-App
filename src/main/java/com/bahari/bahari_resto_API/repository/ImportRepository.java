package com.bahari.bahari_resto_API.repository;

import com.bahari.bahari_resto_API.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImportRepository extends JpaRepository<Import,String> {
}
