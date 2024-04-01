package com.example.OauthCrudExample.service.impl;

import com.example.OauthCrudExample.dto.CarDto;
import com.example.OauthCrudExample.entity.BrandEntity;
import com.example.OauthCrudExample.entity.CarEntity;
import com.example.OauthCrudExample.exception.NotFoundException;
import com.example.OauthCrudExample.mapper.CarMapper;
import com.example.OauthCrudExample.repository.BrandEntityRepository;
import com.example.OauthCrudExample.repository.CarEntityRepository;
import com.example.OauthCrudExample.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService
{
    private final CarEntityRepository carEntityRepository;
    private final BrandEntityRepository brandEntityRepository;
    private final CarMapper carMapper;
    @Override
    public void save(CarDto carDto) {

        CarEntity carEntity=carMapper.fromCarDtoToCarEntity(carDto);
       BrandEntity brandEntity= brandEntityRepository.findByName(carDto.brand())
                .orElse(new BrandEntity(carDto.brand()));
        carEntity.setBrand(brandEntity);
        carEntityRepository.save(carEntity);
    }

    @Override
    public List<CarDto> findAll() {
        return carEntityRepository.findAll().stream()
                .map(carMapper::fromCarEntityToCarDto)
                .toList();
    }

    @Override
    public CarDto findById(Long id) {
        return carMapper.fromCarEntityToCarDto(carEntityRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Car not found by id"+id)));
    }

    @Override
    public void delete(Long id) {
      carEntityRepository.deleteById(id);
    }
}
