package com.back.supermarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionesDto {
    private Long direccionid;
    @NotEmpty
    @Size(min = 3, message = "La dirección deberia tener al menos 3 caracteres")
    private String direccion;
}
