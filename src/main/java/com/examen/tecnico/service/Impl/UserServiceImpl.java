package com.examen.tecnico.service.Impl;

import com.examen.tecnico.model.UserEntity;
import com.examen.tecnico.repository.PurchaseRepository;
import com.examen.tecnico.repository.UserRepository;
import com.examen.tecnico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    @Override
    public Optional<UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}