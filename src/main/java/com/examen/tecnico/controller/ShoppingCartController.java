package com.examen.tecnico.controller;

import com.examen.tecnico.model.ShoppingCart;
import com.examen.tecnico.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {
    @Autowired
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @PostMapping("/save")
    public ResponseEntity<ShoppingCart> addProduct(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
