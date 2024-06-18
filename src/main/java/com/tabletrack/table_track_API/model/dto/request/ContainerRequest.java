package com.tabletrack.table_track_API.model.dto.request;

import com.tabletrack.table_track_API.constant.EColorStatus;
import com.tabletrack.table_track_API.model.entity.product_import.Import;
import com.tabletrack.table_track_API.model.entity.product_import.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContainerRequest {
    private String containerCode;
    private EColorStatus colorStatus;
    private Warehouse warehouseId;
    private Import importId;
    private List<RawMaterialRequest> requestList;
}
