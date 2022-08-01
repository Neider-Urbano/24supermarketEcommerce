package com.back.supermarket.servicios;

import java.util.List;

import com.back.supermarket.dto.CarritosDto;

public interface ICarritosServicio {
    CarritosDto crearCarrito(CarritosDto carritoDto);

    List<CarritosDto> listarCarritos();

    CarritosDto buscarCarrito(Long carritoid);

    List<CarritosDto> buscarCarritoPorCedula(Long cedula);

    CarritosDto actualizarCarrito(CarritosDto CarritoDto, Long carritoid);

    void eliminarCarrito(Long carritoid);

    void eliminarCarritoCedula(Long cedula);
}
