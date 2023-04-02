package com.examen.tecnico.controller;

import com.examen.tecnico.model.ProductEntity;
import com.examen.tecnico.service.ProductServiceImpl;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProduct());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id){
        Optional<ProductEntity> productOptional = Optional.ofNullable(productService.getProductById(id));
        return productOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
