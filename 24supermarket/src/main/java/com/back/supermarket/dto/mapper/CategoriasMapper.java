package com.back.supermarket.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.back.supermarket.dto.CategoriasDto;
import com.back.supermarket.entidades.Categorias;

@Mapper(componentModel = "spring")
public interface CategoriasMapper {
    CategoriasMapper MAPPER = Mappers.getMapper(CategoriasMapper.class);

    CategoriasDto modelToDto(Categorias categorias);

    @InheritInverseConfiguration
    Categorias dtoToModel(CategoriasDto categoriasDto);

    List<CategoriasDto> modelToDtos(List<Categorias> categorias);
}