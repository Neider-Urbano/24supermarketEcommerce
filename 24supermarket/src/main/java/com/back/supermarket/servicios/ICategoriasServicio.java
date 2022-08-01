package com.back.supermarket.servicios;

import java.util.List;

import com.back.supermarket.dto.CategoriasDto;

public interface ICategoriasServicio {
    CategoriasDto crearCategoria(CategoriasDto categoriaDto);

    List<CategoriasDto> listarCategorias();

    CategoriasDto listarCategoriaPorId(Long categoriaid);

    public CategoriasDto actualizarCategoria(Long categoriaid, CategoriasDto direccionDto);

    public void eliminarCategoria(Long categoriaid);
}
