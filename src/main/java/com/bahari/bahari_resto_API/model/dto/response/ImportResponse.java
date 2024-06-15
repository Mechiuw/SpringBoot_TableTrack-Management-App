package com.bahari.bahari_resto_API.model.dto.response;

import com.bahari.bahari_resto_API.constant.EShipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImportResponse {
    private String id;
    private EShipment eShipment;
    private String storeId;
    private String warehouseId;
}
