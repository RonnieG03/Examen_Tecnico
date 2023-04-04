package com.examen.tecnico.controller;

import com.examen.tecnico.model.ShoppingCart;
import com.examen.tecnico.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ShoppingCartService shoppingCartService;
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @PostMapping("/save")
    public ResponseEntity<ShoppingCart> addPrroduct(@RequestBody ShoppingCart shoppingCart){
        LOGGER.info("add products");
        shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getListByUser(){
        LOGGER.info("get List products");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return ResponseEntity.ok(shoppingCartService.getListByClient(username));
    }
    @GetMapping("/count/{user_id}")
    public ResponseEntity<Long> countByUser(@PathVariable("user_id") Long id){
        LOGGER.info("count products by user");
        return ResponseEntity.ok(shoppingCartService.getCountByClient(id));
    }
    @DeleteMapping("/clean/{item_id}")
    public ResponseEntity<ShoppingCart> removeProduct(@PathVariable("item_id") Long id){
        LOGGER.info("delete products");
        shoppingCartService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
