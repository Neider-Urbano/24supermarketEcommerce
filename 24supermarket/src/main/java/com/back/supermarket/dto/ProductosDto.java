package com.back.supermarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.back.supermarket.entidades.Categorias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductosDto {
    private Long productoid;

    @NotEmpty(message = "El nombre del producto no debe ser vacio o nulo")
    @Size(min = 3, message = "El nombre del producto deberia tener al menos 3 caracteres")
    private String nombre;

    @NotNull(message = "El precio del producto no debe ser vacio o nulo")
    private Double precio;

    @NotNull(message = "La cantidad del producto no debe ser vacio o nulo")
    private Double cantidad;

    private String descripcion;
    private String imgurl;
    @NotNull(message = "La cedula del vendedor no debe ser vacio o nulo")
    private Long cedula;
    private Categorias categorias;
}