package com.tabletrack.table_track_API.model.entity;

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
@Table(name = "trx_import_details")
public class ImportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "boarded", nullable = false)
    private Date boarded;

    @Column(name = "arrival",nullable = false)
    private Date arrival;

    @Column(name = "tax",nullable = false)
    private Integer Tax;

    @JoinColumn(name = "importId",referencedColumnName = "id")
    @ManyToOne
    @JsonBackReference
    private Import importId;
}
