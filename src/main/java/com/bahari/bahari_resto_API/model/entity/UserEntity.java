package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private ERole role;
}
