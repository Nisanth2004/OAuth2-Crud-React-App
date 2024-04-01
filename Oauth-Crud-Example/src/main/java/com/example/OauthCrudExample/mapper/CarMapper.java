package com.example.OauthCrudExample.mapper;

import com.example.OauthCrudExample.dto.CarDto;
import com.example.OauthCrudExample.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CarMapper
{
    @Mapping(target = "year",source="yearOfManufacture")
    @Mapping(target = "brand",source = "brand.name")
    CarDto fromCarEntityToCarDto(CarEntity carEntity);

    @Mapping(target = "yearOfManufacture",source="year")
    @Mapping(target = "brand",ignore = true)
    CarEntity fromCarDtoToCarEntity(CarDto carDto);
}
