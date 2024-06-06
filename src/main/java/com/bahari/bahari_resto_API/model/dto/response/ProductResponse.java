package com.bahari.bahari_resto_API.model.dto.response;

import com.bahari.bahari_resto_API.constant.DistributionType;
import com.bahari.bahari_resto_API.constant.EProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String id;
    private String name;
    private Integer price;
    private DistributionType distributionType;
    private EProductType eProductType;
}
