package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.constant.EColorStatus;
import com.bahari.bahari_resto_API.constant.EShipment;
import com.bahari.bahari_resto_API.model.entity.Import;
import com.bahari.bahari_resto_API.model.entity.RawMaterial;
import com.bahari.bahari_resto_API.model.entity.Warehouse;
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
