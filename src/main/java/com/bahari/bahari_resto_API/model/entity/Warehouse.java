package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.ECountry;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "phone_num",nullable = false)
    private String phoneNum;

    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    private ECountry country;
}
