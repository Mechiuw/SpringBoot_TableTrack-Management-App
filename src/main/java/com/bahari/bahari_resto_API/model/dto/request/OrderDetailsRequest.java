package com.bahari.bahari_resto_API.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailsRequest {
    private Integer quantity;
    private String productId;
    private Integer totalPrice;
}
