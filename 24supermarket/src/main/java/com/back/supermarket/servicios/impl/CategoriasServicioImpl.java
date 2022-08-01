package com.back.supermarket.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.supermarket.dto.CategoriasDto;
import com.back.supermarket.dto.mapper.CategoriasMapper;
import com.back.supermarket.entidades.Categorias;
import com.back.supermarket.excepciones.ResourceNotFoundException;
import com.back.supermarket.repositorios.CategoriasRepositorio;
import com.back.supermarket.servicios.ICategoriasServicio;

@Service
public class CategoriasServicioImpl implements ICategoriasServicio {
    @Autowired
    CategoriasRepositorio categoriasRepositorio;

    @Autowired
    CategoriasMapper categoriasMapper;

    @Override
    public List<CategoriasDto> listarCategorias() {
        List<CategoriasDto> categoriasDto = new ArrayList<>();
        List<Categorias> categorias = (List<Categorias>) categoriasRepositorio.findAll();
        categoriasDto = categoriasMapper.modelToDtos(categorias);
        return categoriasDto;
    }

    @Override
    public CategoriasDto listarCategoriaPorId(Long categoriaid) {
        CategoriasDto categoriaDto = new CategoriasDto();
        if (categoriasRepositorio.findById(categoriaid).isPresent()) {
            categoriaDto = categoriasMapper.modelToDto(categoriasRepositorio.findById(categoriaid).get());
        } else {
            throw new ResourceNotFoundException("BuscarCategoriasId", "id", categoriaid);
        }
        return categoriaDto;
    }

    @Override
    public CategoriasDto crearCategoria(CategoriasDto categoriasDto) {
        Categorias categoria = categoriasMapper.dtoToModel(categoriasDto);
        categoria = categoriasRepositorio.save(categoria);
        categoriasDto = categoriasMapper.modelToDto(categoria);
        return categoriasDto;
    }

    @Override
    public CategoriasDto actualizarCategoria(Long categoriaid, CategoriasDto categoriaDto) {
        CategoriasDto categoriaDtoResponse = new CategoriasDto();
        if (categoriasRepositorio.findById(categoriaid).isPresent()) {
            Categorias categoria = categoriasRepositorio.findById(categoriaid).get();
            categoria.setCategoria(categoriaDto.getCategoria());
            categoria = categoriasRepositorio.save(categoria);
            categoriaDtoResponse = categoriasMapper.modelToDto(categoria);
        } else {
            throw new ResourceNotFoundException("ActualizarCategoriasId", "id",
                    categoriaid);
        }
        return categoriaDtoResponse;
    }

    @Override
    public void eliminarCategoria(Long categoriaid) {
        categoriasRepositorio.deleteById(categoriaid);
    }
}
