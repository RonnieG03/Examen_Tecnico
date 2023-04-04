package com.examen.tecnico.service;

import com.examen.tecnico.model.ShoppingCart;

import java.time.LocalDate;
import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> findByClientId(Long clientId);
    List<ShoppingCart> getListByClient(String username);
    public void addProduct(ShoppingCart shoppingCart);
    public void removeProduct(Long id);
    void cleanShoppingCart(Long clientId);
    Long getCountByClient(Long clientId);
    Double getTotalPaymentByDate(LocalDate monthValue);
}
