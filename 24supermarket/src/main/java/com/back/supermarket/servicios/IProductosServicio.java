package com.back.supermarket.servicios;

import java.util.List;

import com.back.supermarket.dto.ProductosDto;
import com.back.supermarket.dto.ProductosPaginacion;

public interface IProductosServicio {
    ProductosDto crearProducto(ProductosDto productosDto, Long categoriaid);

    List<ProductosDto> listarProductos();

    ProductosPaginacion listarProductosPaginacion(int numeroDePagina, int medidaDePagina, String ordenarPor,
            String sortDir);

    ProductosDto buscarProducto(Long productosid);

    List<ProductosDto> buscarProductosPorCategoria(Long categoriaid);

    List<ProductosDto> buscarProductosPorCedula(Long cedula);

    ProductosDto actualizarProducto(ProductosDto productoDto, Long productoid);

    void eliminarProducto(Long productoid);

    void eliminarProductoCedula(Long cedula);
}
