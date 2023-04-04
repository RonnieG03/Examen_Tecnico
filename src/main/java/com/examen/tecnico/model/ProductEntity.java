package com.examen.tecnico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    //private Rating rating;
}
