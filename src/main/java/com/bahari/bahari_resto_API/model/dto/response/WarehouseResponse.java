package com.bahari.bahari_resto_API.model.dto.response;

import com.bahari.bahari_resto_API.constant.ECountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WarehouseResponse {
    private String id;
    private String name;
    private String address;
    private String phoneNum;
    private ECountry eCountry;
}
