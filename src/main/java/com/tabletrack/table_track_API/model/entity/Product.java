package com.tabletrack.table_track_API.model.entity;

import com.tabletrack.table_track_API.constant.DistributionType;
import com.tabletrack.table_track_API.constant.EProductType;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "distribution-type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DistributionType distributionType;

    @Column(name = "product-type",nullable = false)
    @Enumerated(EnumType.STRING)
    private EProductType productType;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.PERSIST)
    private List<OrderDetails> ordersList;
}
