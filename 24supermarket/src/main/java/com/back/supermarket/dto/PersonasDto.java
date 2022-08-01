package com.back.supermarket.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.back.supermarket.entidades.Direcciones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonasDto {
    private Long cedula;

    @NotEmpty(message = "La cedula no debe ser vacio o nulo")
    @Size(min = 3, message = "El nombre deberia tener al menos 3 caracteres")
    private String nombre;

    @NotEmpty(message = "El email no debe ser vacio o nulo")
    @Email
    private String email;

    @NotEmpty(message = "La password no debe ser vacio o nulo")
    @Size(min = 3, message = "La contraseña deberia tener al menos 3 caracteres")
    private String password;
    private Set<Direcciones> direcciones;
}
