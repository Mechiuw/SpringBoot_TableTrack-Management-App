package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
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
public class OrderRequest {
    private LocalDateTime dateTime;
    private String customerId;
    private List<OrderDetailsRequest> orderDetails;
}
