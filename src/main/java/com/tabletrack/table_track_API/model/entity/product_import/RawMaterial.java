package com.tabletrack.table_track_API.model.entity.product_import;

import com.tabletrack.table_track_API.constant.DistributionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tabletrack.table_track_API.model.entity.product_import.Container;
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
    private String stocks;

    @Column(name = "distributionType",nullable = false)
    @Enumerated(EnumType.STRING)
    private DistributionType distributionType;

    @ManyToOne
    @JoinColumn(name = "containerId", referencedColumnName = "id")
    @JsonBackReference
    private Container container;
}
