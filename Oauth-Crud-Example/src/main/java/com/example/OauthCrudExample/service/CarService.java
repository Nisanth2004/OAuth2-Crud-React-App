package com.example.OauthCrudExample.service;

import com.example.OauthCrudExample.dto.CarDto;

import java.util.List;

public interface CarService
{
   void save(CarDto carDto);

   List<CarDto> findAll();

   CarDto findById(Long id);
   void delete(Long id);
}
