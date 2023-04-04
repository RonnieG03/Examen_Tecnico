package com.examen.tecnico.repository;

import com.examen.tecnico.model.ItemsPurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsPurchaseRepository extends JpaRepository<ItemsPurchaseEntity, Long> {
}
