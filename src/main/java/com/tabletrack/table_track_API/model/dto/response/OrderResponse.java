package com.tabletrack.table_track_API.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String id;
    private LocalDateTime dateTime;
    private String customerId;
    private List<OrderDetailsResponse> orderDetails;
}
