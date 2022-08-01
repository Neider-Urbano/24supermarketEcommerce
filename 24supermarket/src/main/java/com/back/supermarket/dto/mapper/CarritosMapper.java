package com.back.supermarket.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.back.supermarket.dto.CarritosDto;
import com.back.supermarket.entidades.Carritos;

@Mapper(componentModel = "spring")
public interface CarritosMapper {
    CarritosMapper MAPPER = Mappers.getMapper(CarritosMapper.class);

    CarritosDto modelToDto(Carritos carrito);

    @InheritInverseConfiguration
    Carritos dtoToModel(CarritosDto carritoDto);

    List<CarritosDto> modelToDtos(List<Carritos> carritos);
}