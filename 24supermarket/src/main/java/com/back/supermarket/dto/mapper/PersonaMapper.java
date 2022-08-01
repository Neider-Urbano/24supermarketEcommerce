package com.back.supermarket.dto.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.back.supermarket.dto.PersonasDto;
import com.back.supermarket.entidades.Personas;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaMapper MAPPER = Mappers.getMapper(PersonaMapper.class);

    PersonasDto modelToDto(Personas persona);

    @InheritInverseConfiguration
    Personas dtoToModel(PersonasDto PersonaDto);

    List<PersonasDto> modelToDtos(List<Personas> personas);
}
