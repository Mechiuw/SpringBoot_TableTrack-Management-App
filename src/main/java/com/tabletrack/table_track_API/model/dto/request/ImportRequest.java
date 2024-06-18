package com.tabletrack.table_track_API.model.dto.request;

import com.tabletrack.table_track_API.constant.EShipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImportRequest {
    private String storeId;
    private String warehouseId;
    private EShipment shipment;
    private List<ContainerRequest> containerRequestList;
    private List<ImportDetailsRequest> importDetailsRequests;
}
