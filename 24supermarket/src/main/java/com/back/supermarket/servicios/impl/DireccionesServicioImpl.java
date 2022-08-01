package com.back.supermarket.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.back.supermarket.dto.DireccionesDto;
import com.back.supermarket.dto.mapper.DireccionesMapper;
import com.back.supermarket.entidades.Direcciones;
import com.back.supermarket.entidades.Personas;
import com.back.supermarket.excepciones.BlogAppException;
import com.back.supermarket.excepciones.ResourceNotFoundException;
import com.back.supermarket.repositorios.DireccionesRepositorio;
import com.back.supermarket.repositorios.PersonaRepositorio;
import com.back.supermarket.servicios.IDireccionesServicio;

@Service
public class DireccionesServicioImpl
        implements IDireccionesServicio {
    @Autowired
    DireccionesRepositorio direccionesRepositorio;

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    DireccionesMapper direccionesMapper;

    @Override
    public DireccionesDto crearDireccion(Long cedula, DireccionesDto direccionesDto) {
        if (direccionesDto.getDireccionid() == null && personaRepositorio.findById(cedula).isPresent()) {
            Personas persona = personaRepositorio.findById(cedula).get();

            Direcciones direccion = new Direcciones();
            direccion = direccionesMapper.dtoToModel(direccionesDto);

            direccion.setPersonas(persona);
            direccion = direccionesRepositorio.save(direccion);
            direccionesDto = direccionesMapper.modelToDto(direccion);
            return direccionesDto;
        } else {
            throw new ResourceNotFoundException("DireccionesBuscarPersonaId", "id", cedula);
        }
    }

    @Override
    public List<DireccionesDto> listarDireccionesPorCedula(Long cedula) {
        List<Direcciones> direcciones = direccionesRepositorio.findByCedula(cedula);
        return direcciones.stream().map(direccion -> direccionesMapper.modelToDto(direccion))
                .collect(Collectors.toList());
    }

    @Override
    public DireccionesDto listarDireccionPorId(Long cedula, Long direccionid) {
        Personas persona = personaRepositorio.findById(cedula).get();

        Direcciones direccion = direccionesRepositorio.findById(direccionid).get();

        if (!direccion.getPersonas().getCedula().equals(persona.getCedula())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La direccion no pertenece a la persona");
        }
        DireccionesDto direccionDto = new DireccionesDto();
        direccionDto = direccionesMapper.modelToDto(direccion);
        return direccionDto;
    }

    @Override
    public DireccionesDto actualizarDireccion(Long cedula, Long direccionid, DireccionesDto direccionDto) {
        Personas persona = personaRepositorio.findById(cedula).get();

        Direcciones direccion = direccionesRepositorio.findById(direccionid).get();

        if (!direccion.getPersonas().getCedula().equals(persona.getCedula())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La direccion no pertenece a la persona");
        }

        direccion.setDireccion(direccionDto.getDireccion());
        Direcciones direccionActualizada = direccionesRepositorio.save(direccion);
        direccionDto = direccionesMapper.modelToDto(direccionActualizada);
        return direccionDto;
    }

    @Override
    public void eliminarDireccion(Long cedula, Long direccionid) {
        Personas persona = personaRepositorio.findById(cedula).get();
        Direcciones direccion = direccionesRepositorio.findById(direccionid).get();

        if (!direccion.getPersonas().getCedula().equals(persona.getCedula())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La Direccion no pertenece a la persona");
        }

        direccionesRepositorio.delete(direccion);
    }
}
