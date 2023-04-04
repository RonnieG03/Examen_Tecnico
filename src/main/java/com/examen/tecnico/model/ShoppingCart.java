package com.examen.tecnico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<ProductEntity> product;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private UserEntity client;
    private int quantity;
    private Double totalPayment;
    private LocalDate date;
    private Boolean isVip;
    private Boolean isPromotableByDate;
}
