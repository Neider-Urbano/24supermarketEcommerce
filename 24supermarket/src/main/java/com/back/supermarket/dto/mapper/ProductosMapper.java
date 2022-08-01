package com.back.supermarket.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.back.supermarket.dto.ProductosDto;
import com.back.supermarket.entidades.Productos;

@Mapper(componentModel = "spring")
public interface ProductosMapper {
    ProductosDto modelToDto(Productos productos);

    @InheritInverseConfiguration
    Productos dtoToModel(ProductosDto productosDto);

    List<ProductosDto> modelToDtos(List<Productos> Productos);
}
