package com.bahari.bahari_resto_API.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailsResponse {
    private String id;
    private Integer quantity;
    private String orderId;
    private String productId;
}
