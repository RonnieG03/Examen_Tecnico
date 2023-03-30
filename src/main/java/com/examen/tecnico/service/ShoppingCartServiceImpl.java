package com.examen.tecnico.service;

import com.examen.tecnico.model.ShoppingCart;
import com.examen.tecnico.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    @Override
    public List<ShoppingCart> findByClientId(Long clientId) {
        return null;
    }
    @Override
    public List<ShoppingCart> getListByClient(String userName) {
        return shoppingCartRepository.findByClientUserName(userName);
    }
    @Override
    public void addProduct(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }
    @Override
    public void removeProduct(Long id) {
        shoppingCartRepository.deleteById(id);
    }
    @Override
    public void cleanShoppingCart(Long clientId) {
        shoppingCartRepository.deleteByClientId(clientId);
    }
    @Override
    public Long getCountByClient(Long clientId) {
        return shoppingCartRepository.countByClientId(clientId);
    }
}
