package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.constant.EShipment;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContainerRequest {
    private String containerCode;
    private EShipment eshipment;
    private Warehouse warehouse;
}
