package com.examen.tecnico.repository;

import com.examen.tecnico.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByClientUsername(String username);
    void deleteByClientId(Long clientId);
    public Long countByClientId(Long clientId);
    Double findTotalPaymentByDate(LocalDate monthValue);

}
