package com.tabletrack.table_track_API.model.dto.response;

import com.tabletrack.table_track_API.constant.EColorStatus;
import com.tabletrack.table_track_API.model.entity.RawMaterial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<RawMaterial> rawMaterialList;
}
