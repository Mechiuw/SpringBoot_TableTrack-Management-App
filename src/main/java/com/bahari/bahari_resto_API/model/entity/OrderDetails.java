package com.bahari.bahari_resto_API.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trx_order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "quantity" , nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    @JsonBackReference
    private Product product;

}
