package com.back.supermarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriasDto {

    private Long categoriaid;

    @NotEmpty(message = "La categoria no deberia ser null o vacia")
    @Size(min = 3, message = "La categoria deberia tener al menos 3 caracteres")
    private String categoria;
}
