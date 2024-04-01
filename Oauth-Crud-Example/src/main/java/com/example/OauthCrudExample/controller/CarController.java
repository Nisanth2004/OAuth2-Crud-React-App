package com.example.OauthCrudExample.controller;

import com.example.OauthCrudExample.dto.CarDto;
import com.example.OauthCrudExample.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@CrossOrigin
public class CarController
{
  private  final CarService carService;
  @GetMapping("/{id}")
    public CarDto findById(@PathVariable Long id){
      return carService.findById(id);
  }

  @GetMapping
  public List<CarDto> findAll()
  {
  return carService.findAll();
  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody CarDto carDto)
  {
      carService.save(carDto);
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') ")
  public ResponseEntity<Void> delete(@PathVariable Long id)
  {
      carService.delete(id);
      return ResponseEntity.status(HttpStatus.OK).build();
  }
}
