package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.ERole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "m_user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private ERole role;
}
