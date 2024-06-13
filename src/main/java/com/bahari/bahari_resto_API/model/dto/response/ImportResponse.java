package com.bahari.bahari_resto_API.model.dto.response;

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
    private String storeId;
    private String warehouseId;
}
