package com.example.OauthCrudExample.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CarDto(Long id,
                     @NotEmpty(message = "Car should have a brand") String brand,
                     @NotEmpty(message = "Car should have a model")  String model,
                     @NotNull(message = "Car should have a year") Integer year
                     )
{
    // immutable ,only one instance will be reated
}
