package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.constant.ECountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WarehouseRequest {
    private String name;
    private String address;
    private String phoneNum;
    private ECountry country;
    private List<ContainerRequest> containerRequestList;
}
