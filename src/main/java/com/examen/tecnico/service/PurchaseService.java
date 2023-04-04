package com.examen.tecnico.service;

import com.examen.tecnico.model.PurchaseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    public PurchaseEntity save(PurchaseEntity purchase);
    public List<PurchaseEntity> getPurchaseByDate(LocalDate date);
    public List<PurchaseEntity> getPurchaseByUser(String username);
    public List<PurchaseEntity> getAllPurchase();
    public void deletePurchase(PurchaseEntity purchase);

}
