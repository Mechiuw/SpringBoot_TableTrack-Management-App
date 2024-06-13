package com.bahari.bahari_resto_API.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImportDetailsResponse {
    private String id;
    private Date boarded;
    private Date arrival;
    private Integer tax;
    private String importId;
}
