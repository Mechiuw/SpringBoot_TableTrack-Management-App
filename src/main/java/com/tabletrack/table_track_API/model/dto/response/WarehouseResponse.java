package com.tabletrack.table_track_API.model.dto.response;

import com.tabletrack.table_track_API.constant.ECountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
