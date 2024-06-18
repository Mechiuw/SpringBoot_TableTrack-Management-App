package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.authentication.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
}
