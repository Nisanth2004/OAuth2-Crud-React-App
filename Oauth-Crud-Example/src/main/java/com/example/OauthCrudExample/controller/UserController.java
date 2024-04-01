package com.example.OauthCrudExample.controller;

import com.example.OauthCrudExample.entity.UserRole;
import com.example.OauthCrudExample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    // to change role user to admin
    private final UserService userService;
    @PostMapping("/user/{email} ")
    public void changeToAdmin(@PathVariable String email)
    {
        userService.findByEmail(email).ifPresent(userEntity -> {
            userEntity.setRole(UserRole.ROLE_ADMIN);
             userService.save(userEntity);
        });

    }
}
