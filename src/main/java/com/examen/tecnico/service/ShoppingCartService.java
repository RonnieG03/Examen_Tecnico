package com.examen.tecnico.service;

import com.examen.tecnico.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> findByClientId(Long clientId);
    List<ShoppingCart> getListByClient(String userName);
    public void addProduct(ShoppingCart shoppingCart);
    public void removeProduct(Long id);
    void cleanShoppingCart(Long clientId);
    Long getCountByClient(Long clientId);
}
