package com.tabletrack.table_track_API.model.dto.request;

import com.tabletrack.table_track_API.constant.ECountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WarehouseRequest {
    private String name;
    private String address;
    private String phoneNum;
    private ECountry country;
}
