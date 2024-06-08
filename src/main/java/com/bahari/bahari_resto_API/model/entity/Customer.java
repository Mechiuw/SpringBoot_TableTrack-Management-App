package com.bahari.bahari_resto_API.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name" , nullable = false, length = 50)
    private String name;

    @Column(name = "address" , nullable = false, length = 30)
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNum", nullable = false)
    private String phoneNum;
}
