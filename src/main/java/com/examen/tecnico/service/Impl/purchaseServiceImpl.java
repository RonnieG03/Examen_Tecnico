package com.examen.tecnico.service.Impl;

import com.examen.tecnico.model.PurchaseEntity;
import com.examen.tecnico.repository.PurchaseRepository;
import com.examen.tecnico.repository.UserRepository;
import com.examen.tecnico.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class purchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    @Autowired
    public purchaseServiceImpl(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    @Override
    public PurchaseEntity save(PurchaseEntity purchase) {
        return purchaseRepository.save(purchase);
    }
    @Override
    public List<PurchaseEntity> getPurchaseByDate(LocalDate date) {
        return purchaseRepository.findByPurchaseDate(date);
    }
    @Override
    public List<PurchaseEntity> getPurchaseByUser(String username) {
        return purchaseRepository.findByClient(username);
    }
    @Override
    public List<PurchaseEntity> getAllPurchase() {
        return purchaseRepository.findAll();
    }
    @Override
    public void deletePurchase(PurchaseEntity purchase) {
        purchaseRepository.delete(purchase);
    }
}
