package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_user_credential")
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
