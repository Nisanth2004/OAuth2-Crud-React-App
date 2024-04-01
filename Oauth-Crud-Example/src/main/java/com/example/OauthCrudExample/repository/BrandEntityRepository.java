package com.example.OauthCrudExample.repository;

import com.example.OauthCrudExample.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandEntityRepository  extends JpaRepository<BrandEntity,Long>
{

    Optional<BrandEntity> findByName(String name);
}
