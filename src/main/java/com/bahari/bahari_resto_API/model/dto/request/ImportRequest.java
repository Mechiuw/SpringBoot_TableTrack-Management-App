package com.bahari.bahari_resto_API.model.dto.request;

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
    private List<ContainerRequest> containerRequestList;
    private List<ImportDetailsRequest> importDetailsRequests;
}
