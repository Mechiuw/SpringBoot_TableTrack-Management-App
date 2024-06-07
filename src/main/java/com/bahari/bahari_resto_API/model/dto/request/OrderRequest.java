package com.bahari.bahari_resto_API.model.dto.request;

import com.bahari.bahari_resto_API.model.dto.response.OrderDetailsResponse;
import com.bahari.bahari_resto_API.model.entity.OrderDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private LocalDateTime dateTime;
    private String customerId;
    @NotEmpty
    private List<OrderDetailsRequest> orderDetails;
}
