package com.tabletrack.table_track_API.repository;

import com.tabletrack.table_track_API.model.entity.product_import.ImportDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportDetailsRepository extends JpaRepository<ImportDetails,String> {
}
