package com.example.OauthCrudExample.service;

import com.example.OauthCrudExample.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String email);
    void save(UserEntity user);
}
