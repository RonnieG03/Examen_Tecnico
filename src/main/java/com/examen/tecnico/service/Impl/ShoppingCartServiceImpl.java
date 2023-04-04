package com.examen.tecnico.service.Impl;

import com.examen.tecnico.model.ProductEntity;
import com.examen.tecnico.model.ShoppingCart;
import com.examen.tecnico.repository.ShoppingCartRepository;
import com.examen.tecnico.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
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
    public List<ShoppingCart> getListByClient(String username) {
        return shoppingCartRepository.findByClientUsername(username);
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

    @Override
    public Double getTotalPaymentByDate(LocalDate monthValue) {
        return shoppingCartRepository.findTotalPaymentByDate(monthValue);
    }
    public void updateVIP() {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        LocalDate today = LocalDate.now();
        for (ShoppingCart cart : carts) {
            Double totalPayment = shoppingCartRepository.findTotalPaymentByDate(LocalDate.ofEpochDay(today.getMonthValue()));
            if (totalPayment > 10000) {
                cart.setIsVip(true);
            } else{
                cart.setIsVip(false);
            }
            cart.setTotalPayment(totalPayment);
            shoppingCartRepository.save(cart);
        }
    }
    public double calculateValueCart(ShoppingCart cart) {
        Double total = 0.0;
        ShoppingCart shoppingCart = new ShoppingCart();
        List<ProductEntity> products = cart.getProduct();
        int quantityProducts = products.size();
        Boolean isVip = shoppingCart.getIsVip();
        boolean isPromotableByDate = shoppingCart.getIsPromotableByDate();

        // Calculo del total sin descuentos ni promociones
        for (ProductEntity product : products) {
            total += product.getPrice() * cart.getQuantity();
        }

        if (quantityProducts == 4) {
            // Descuento del 25%
            total *= 0.75;
        } else if (quantityProducts > 10) {
            if (!cart.getIsVip() && !cart.getIsPromotableByDate()) {
                // Descuento de $100
                total -= 100;
            } else if (isPromotableByDate) {
                // Descuento de $300
                total -= 300;
            } else if (isVip) {
                // Se bonifica el producto más barato y descuento de $500
                ProductEntity productCheapest = null;
                for (ProductEntity product : products) {
                    if (productCheapest == null || product.getPrice() < productCheapest.getPrice()) {
                        productCheapest = product;
                    }
                }
                if (productCheapest != null) {
                    total -= productCheapest.getPrice();
                }
                total -= 500;
            }
        }

        return total;
    }

}
