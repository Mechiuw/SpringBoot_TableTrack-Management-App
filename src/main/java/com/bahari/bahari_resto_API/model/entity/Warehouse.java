package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.ECountry;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "warehouseId",cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Container> containers;
}
