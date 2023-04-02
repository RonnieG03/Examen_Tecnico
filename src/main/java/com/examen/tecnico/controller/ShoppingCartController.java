package com.examen.tecnico.controller;

import com.examen.tecnico.model.ShoppingCart;
import com.examen.tecnico.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @PostMapping("/save")
    public ResponseEntity<ShoppingCart> addPrroduct(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getListByUser(){
        return null;
    }

}
