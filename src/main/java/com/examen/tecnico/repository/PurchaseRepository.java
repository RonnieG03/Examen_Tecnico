package com.examen.tecnico.repository;

import com.examen.tecnico.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    public List<PurchaseEntity> findByPurchaseDate(LocalDate date);
    public List<PurchaseEntity> findByClient(String username);
    public List<PurchaseEntity> findAll();


}
