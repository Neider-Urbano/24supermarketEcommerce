package com.back.supermarket.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.back.supermarket.dto.PersonasDto;
import com.back.supermarket.dto.mapper.PersonaMapper;
import com.back.supermarket.entidades.Personas;
import com.back.supermarket.excepciones.ResourceNotFoundException;
import com.back.supermarket.repositorios.PersonaRepositorio;
import com.back.supermarket.servicios.IPersonaServicio;

@Service
public class PersonaServicioImpl implements IPersonaServicio {
    @Autowired
    PersonaRepositorio PersonaRepositorio;

    @Autowired
    PersonaMapper PersonaMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public PersonasDto crearPersona(PersonasDto personaDto) {
        Personas persona = new Personas();
        persona = PersonaMapper.dtoToModel(personaDto);
        persona = PersonaRepositorio.save(persona);
        personaDto = PersonaMapper.modelToDto(persona);
        return personaDto;
    }

    @Override
    public List<PersonasDto> listarPersonas() {
        List<PersonasDto> personasDtos = new ArrayList<>();
        List<Personas> persona = (List<Personas>) PersonaRepositorio.findAll();
        personasDtos = PersonaMapper.modelToDtos(persona);
        return personasDtos;
    }

    @Override
    public PersonasDto buscarPersona(Long cedula) {
        PersonasDto personaDto = new PersonasDto();
        if (PersonaRepositorio.findById(cedula).isPresent()) {
            personaDto = PersonaMapper.modelToDto(PersonaRepositorio.findById(cedula).get());
        } else {
            throw new ResourceNotFoundException("BuscarPersonaId", "id", cedula);
        }
        return personaDto;
    }

    @Override
    public PersonasDto buscarPersonaContraseña(Long cedula, String password) {
        PersonasDto personaDto = new PersonasDto();
        Personas persona = PersonaRepositorio.findByCedulaContraseña(cedula, password);
        personaDto = PersonaMapper.modelToDto(persona);
        return personaDto;
    }

    @Override
    public PersonasDto actualizarPersona(PersonasDto personaDto, Long cedula) {
        PersonasDto personaDtoResponse = new PersonasDto();
        if (PersonaRepositorio.findById(cedula).isPresent()) {
            Personas persona = PersonaRepositorio.findById(cedula).get();
            persona.setNombre(personaDto.getNombre());
            persona.setEmail(personaDto.getEmail());
            persona.setPassword(personaDto.getPassword());
            persona = PersonaRepositorio.save(persona);
            personaDtoResponse = PersonaMapper.modelToDto(persona);
        } else {
            throw new ResourceNotFoundException("ActualizarPersonaId", "id", cedula);
        }
        return personaDtoResponse;
    }

    @Override
    public void eliminarPersona(Long cedula) {
        PersonaRepositorio.deleteById(cedula);
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Personas persona = PersonaRepositorio.findByNombre(nombre);
        if (persona == null) {
            throw new UsernameNotFoundException("User not found with username: " + nombre);
        }
        return new User(persona.getNombre(), persona.getPassword(),
                new ArrayList<>());
    }
}
