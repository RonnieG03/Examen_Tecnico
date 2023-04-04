package com.examen.tecnico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items_purchases")
public class ItemsPurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;

}
