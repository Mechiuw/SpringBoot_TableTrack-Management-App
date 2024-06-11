package com.bahari.bahari_resto_API.model.entity;

import com.bahari.bahari_resto_API.constant.EShipment;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "m_container")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "containerCode",nullable = false)
    private String containerCode;

    @JoinColumn(name = "warehouse",nullable = false,referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    @ManyToOne
    private Warehouse warehouseId;

    @Column(name = "shipment",nullable = false)
    @Enumerated(EnumType.STRING)
    private EShipment shipment;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RawMaterial> rawMaterialList;
}
