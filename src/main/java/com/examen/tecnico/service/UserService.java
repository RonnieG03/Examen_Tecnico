package com.examen.tecnico.service;

import com.examen.tecnico.model.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findById(Long id);
    UserEntity save(UserEntity userEntity);
    public Boolean existsByUsername(String username);
    Optional<UserEntity> getByUsername(String username);
}
