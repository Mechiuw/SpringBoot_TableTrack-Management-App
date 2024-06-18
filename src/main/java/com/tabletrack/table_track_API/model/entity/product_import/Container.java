package com.tabletrack.table_track_API.model.entity.product_import;

import com.tabletrack.table_track_API.constant.EColorStatus;
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

    @Column(name = "colorStatus",nullable = true)
    @Enumerated(EnumType.STRING)
    private EColorStatus status;


    @JoinColumn(name = "importId",nullable = true,referencedColumnName = "id", columnDefinition = "varchar(255) default 'UNORDERED'")
    @JsonBackReference
    @ManyToOne
    private Import importId;

    @ManyToOne
    @JoinColumn(name = "warehouseId", nullable = true,referencedColumnName = "id",columnDefinition = "varchar(255) default 'UNPLACED'")
    @JsonBackReference
    private Warehouse warehouseId;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RawMaterial> rawMaterialList;

}
