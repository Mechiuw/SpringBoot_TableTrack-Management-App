package com.tabletrack.table_track_API.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImportDetailsRequest {
    private Date boarded;
    private Date arrival;
    private Integer tax;
    private String importId;
}
