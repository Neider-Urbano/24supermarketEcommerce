package com.back.supermarket.servicios;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.back.supermarket.dto.PersonasDto;

public interface IPersonaServicio extends UserDetailsService {
    PersonasDto crearPersona(PersonasDto PersonaDto);

    List<PersonasDto> listarPersonas();

    PersonasDto buscarPersona(Long cedula);

    PersonasDto buscarPersonaContraseña(Long cedula, String password);

    PersonasDto actualizarPersona(PersonasDto personaDto, Long cedula);

    void eliminarPersona(Long cedula);
}
