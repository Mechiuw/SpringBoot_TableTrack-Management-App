package com.bahari.bahari_resto_API.model.dto.response;

import com.bahari.bahari_resto_API.constant.EColorStatus;
import com.bahari.bahari_resto_API.constant.EShipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContainerResponse {
    private String id;
    private String containerCode;
    private EColorStatus colorStatus;
    private String importId;
    private String warehouseId;
}
