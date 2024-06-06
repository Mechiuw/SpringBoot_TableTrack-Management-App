package com.bahari.bahari_resto_API.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
}
