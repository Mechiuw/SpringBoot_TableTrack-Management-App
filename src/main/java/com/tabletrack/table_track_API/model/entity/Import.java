package com.tabletrack.table_track_API.model.entity;

import com.tabletrack.table_track_API.constant.EShipment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_import")
public class Import {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = "store",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    @ManyToOne
    private Store store;

    @JoinColumn(name = "warehouse",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    @ManyToOne
    private Warehouse warehouse;


    @OneToMany(mappedBy = "importId", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Container> containers;

    @Column(name = "shipment",nullable = false)
    @Enumerated(EnumType.STRING)
    private EShipment shipment;

    @OneToMany(mappedBy = "importId",cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ImportDetails> importDetails;

}
