package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.constant.DistributionType;
import com.bahari.bahari_resto_API.model.entity.Container;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialRequest {
    // save entity
    private String name;
    private Date expDate;
    private Integer price;
    private String manufacture;
    private String stocks;
    private DistributionType distributionType;
    //
    //update entity
    private String containerId;
}
