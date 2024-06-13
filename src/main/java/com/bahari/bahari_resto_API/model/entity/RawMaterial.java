package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.DistributionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_raw_material")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "expDate",nullable = false)
    private Date expDate;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "manufacture",nullable = false)
    private String manufacture;

    @Column(name = "quantity",nullable = false)
    private String quantity;

    @Column(name = "distributionType",nullable = false)
    @Enumerated(EnumType.STRING)
    private DistributionType distributionType;

    @ManyToOne
    @JoinColumn(name = "containerId", referencedColumnName = "id")
    @JsonBackReference
    private Container container;
}
