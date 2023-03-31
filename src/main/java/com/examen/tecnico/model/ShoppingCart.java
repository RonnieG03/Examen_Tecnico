package com.examen.tecnico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private ProductEntity product;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private UserEntity client;
    private int amount;
}
