package com.tabletrack.table_track_API.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailsRequest {
    private String orderId;
    private Integer quantity;
    private String productId;
}
