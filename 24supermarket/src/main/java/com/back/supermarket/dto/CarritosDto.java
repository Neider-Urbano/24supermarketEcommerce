package com.back.supermarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritosDto {
    private Long carritoid;

    @NotNull(message = "El id de la persona no debe ser vacio o nulo")
    private Long cedula;

    @NotNull(message = "El id del producto no debe ser vacio o nulo")
    private Long productoid;

    @NotNull(message = "La cantidad del producto no debe ser vacio o nulo")
    private Long cantidadproducto;

    @NotEmpty(message = "El nombre del producto no debe ser vacio o nulo")
    @Size(min = 3, message = "El nombre del producto deberia tener al menos 3 caracteres")
    private String nombreproducto;
}
