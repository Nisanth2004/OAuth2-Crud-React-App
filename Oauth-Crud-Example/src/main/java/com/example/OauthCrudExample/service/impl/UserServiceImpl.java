package com.example.OauthCrudExample.service.impl;

import com.example.OauthCrudExample.entity.UserEntity;
import com.example.OauthCrudExample.repository.UserEntityRepository;
import com.example.OauthCrudExample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public void save(UserEntity user) {
        userEntityRepository.save(user);

    }
}
