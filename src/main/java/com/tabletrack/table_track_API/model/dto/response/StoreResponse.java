package com.tabletrack.table_track_API.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private String id;
    private String name;
    private String address;
    private String phoneNum;
}
