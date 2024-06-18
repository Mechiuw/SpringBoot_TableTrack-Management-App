package com.tabletrack.table_track_API.model.dto.response;

import com.tabletrack.table_track_API.constant.DistributionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RawMaterialResponse {
    private String id;
    private String name;
    private Date expDate;
    private Integer price;
    private String manufacture;
    private String stocks;
    private DistributionType distributionType;
    private String containerId;
}
