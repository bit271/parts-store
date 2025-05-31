package com.bit.partsstore.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_parts")
public class OrderPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "detail_id", nullable = false)
    private Part part;

    @Column(nullable = false)
    private Integer count;
}
