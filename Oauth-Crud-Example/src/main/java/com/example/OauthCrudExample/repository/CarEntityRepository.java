package com.example.OauthCrudExample.repository;

import com.example.OauthCrudExample.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEntityRepository extends JpaRepository<CarEntity,Long> {
}
