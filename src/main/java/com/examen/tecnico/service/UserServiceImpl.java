package com.examen.tecnico.service;

import com.examen.tecnico.model.UserEntity;
import com.examen.tecnico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
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