package com.back.supermarket.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.back.supermarket.dto.CarritosDto;
import com.back.supermarket.dto.mapper.CarritosMapper;
import com.back.supermarket.entidades.Carritos;
import com.back.supermarket.repositorios.CarritosRepositorio;
import com.back.supermarket.excepciones.BlogAppException;
import com.back.supermarket.excepciones.ResourceNotFoundException;
import com.back.supermarket.repositorios.PersonaRepositorio;
import com.back.supermarket.servicios.ICarritosServicio;

@Service
public class CarritosServicioImpl implements ICarritosServicio {
    @Autowired
    CarritosRepositorio carritoRepositorio;

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    CarritosMapper carritoMapper;

    @Override
    public CarritosDto crearCarrito(CarritosDto carritoDto) {
        Carritos carrito = carritoMapper.dtoToModel(carritoDto);
        carrito = carritoRepositorio.save(carrito);
        carritoDto = carritoMapper.modelToDto(carrito);
        return carritoDto;
    }

    @Override
    public List<CarritosDto> listarCarritos() {
        List<CarritosDto> carritosDto = new ArrayList<>();
        List<Carritos> carrito = (List<Carritos>) carritoRepositorio.findAll();
        carritosDto = carritoMapper.modelToDtos(carrito);
        return carritosDto;
    }

    @Override
    public CarritosDto buscarCarrito(Long carritoid) {
        CarritosDto carritoDto = new CarritosDto();
        if (carritoRepositorio.findById(carritoid).isPresent()) {
            carritoDto = carritoMapper.modelToDto(carritoRepositorio.findById(carritoid).get());
        } else {
            throw new ResourceNotFoundException("BuscarCarritoId", "id", carritoid);
        }
        return carritoDto;
    }

    @Override
    public CarritosDto actualizarCarrito(CarritosDto carritoDto, Long carritoid) {
        CarritosDto carritoDtoResponse = new CarritosDto();
        if (carritoRepositorio.findById(carritoid).isPresent()) {
            Carritos carrito = carritoRepositorio.findById(carritoid).get();
            carrito.setCantidadproducto(carritoDto.getCantidadproducto());
            carrito.setNombreproducto(carritoDto.getNombreproducto());
            carrito = carritoRepositorio.save(carrito);
            carritoDtoResponse = carritoMapper.modelToDto(carrito);
        } else {
            throw new ResourceNotFoundException("ActualizarCarritoId", "id", carritoid);
        }
        return carritoDtoResponse;
    }

    @Override
    public List<CarritosDto> buscarCarritoPorCedula(Long cedula) {

        if (!personaRepositorio.findById(cedula).isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El IdPersona no existe");
        }
        List<Carritos> carritos = carritoRepositorio.findByCedula(cedula);
        return carritos.stream().map(carrito -> carritoMapper.modelToDto(carrito))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCarrito(Long carritoid) {
        carritoRepositorio.deleteById(carritoid);
    }

    @Override
    public void eliminarCarritoCedula(Long cedula) {
        carritoRepositorio.deleteByCedula(cedula);
    }
}
