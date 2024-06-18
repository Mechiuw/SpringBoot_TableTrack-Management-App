package com.tabletrack.table_track_API.model.dto.request;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private String email;
    private String phoneNum;
}
