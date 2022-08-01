package com.back.supermarket.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.back.supermarket.dto.ProductosDto;
import com.back.supermarket.dto.ProductosPaginacion;
import com.back.supermarket.dto.mapper.ProductosMapper;
import com.back.supermarket.entidades.Categorias;
import com.back.supermarket.entidades.Productos;
import com.back.supermarket.excepciones.BlogAppException;
import com.back.supermarket.excepciones.ResourceNotFoundException;
import com.back.supermarket.repositorios.CategoriasRepositorio;
import com.back.supermarket.repositorios.ProductosRepositorio;
import com.back.supermarket.repositorios.ProductosRepositorioSort;
import com.back.supermarket.servicios.IProductosServicio;

@Service
public class ProductosServicioImpl implements IProductosServicio {

    @Autowired
    ProductosRepositorio productosRepositorio;

    @Autowired
    ProductosRepositorioSort productosRepositorioSort;

    @Autowired
    ProductosMapper productosMapper;

    @Autowired
    CategoriasRepositorio categoriasRepositorio;

    @Override
    public List<ProductosDto> listarProductos() {
        List<ProductosDto> productosDto = new ArrayList<>();
        List<Productos> productos = (List<Productos>) productosRepositorio.findAll();
        productosDto = productosMapper.modelToDtos(productos);
        return productosDto;
    }

    @Override
    public ProductosDto buscarProducto(Long productoid) {
        ProductosDto productoDto = new ProductosDto();
        if (productosRepositorio.findById(productoid).isPresent()) {
            productoDto = productosMapper.modelToDto(productosRepositorio.findById(productoid).get());
        } else {
            throw new ResourceNotFoundException("BuscarProductosId", "id", productoid);
        }
        return productoDto;
    }

    @Override
    public ProductosDto crearProducto(ProductosDto productoDto, Long categoriaid) {
        if (productoDto.getProductoid() == null) {
            Productos producto = new Productos();
            producto = productosMapper.dtoToModel(productoDto);

            Categorias categorias = categoriasRepositorio.findById(categoriaid).get();
            producto.setCategorias(categorias);
            producto = productosRepositorio.save(producto);
            productoDto = productosMapper.modelToDto(producto);
        }
        return productoDto;
    }

    @Override
    public ProductosDto actualizarProducto(ProductosDto productoDto, Long productoid) {
        ProductosDto productoDtoResponse = new ProductosDto();
        if (productosRepositorio.findById(productoid).isPresent()) {
            Productos producto = productosRepositorio.findById(productoid).get();
            producto.setNombre(productoDto.getNombre());
            producto.setPrecio(productoDto.getPrecio());
            producto.setCantidad(productoDto.getCantidad());
            producto.setDescripcion(productoDto.getDescripcion());
            producto.setImgurl(productoDto.getImgurl());
            producto.setCedula(productoDto.getCedula());
            producto = productosRepositorio.save(producto);
            productoDtoResponse = productosMapper.modelToDto(producto);
        } else {
            throw new ResourceNotFoundException("ActualizarProductosId", "id",
                    productoid);
        }
        return productoDtoResponse;
    }

    @Override
    public List<ProductosDto> buscarProductosPorCategoria(Long categoriaid) {

        if (!categoriasRepositorio.findById(categoriaid).isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El IdPersona no existe");
        }
        List<Productos> productos = productosRepositorio.findByCategoria(categoriaid);
        return productos.stream().map(producto -> productosMapper.modelToDto(producto))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductosDto> buscarProductosPorCedula(Long cedula) {
        List<Productos> productos = productosRepositorio.findByCedula(cedula);
        return productos.stream().map(producto -> productosMapper.modelToDto(producto))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Long productoid) {
        productosRepositorio.deleteById(productoid);
    }

    @Override
    public void eliminarProductoCedula(Long cedula) {
        productosRepositorio.deleteByCedula(cedula);
    }

    @Override
    public ProductosPaginacion listarProductosPaginacion(int numeroDePagina, int medidaDePagina, String ordenarPor,
            String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Productos> Productoss = productosRepositorioSort.findAll(pageable);

        List<Productos> listaProductoss = Productoss.getContent();
        List<ProductosDto> contenido = listaProductoss.stream().map(productos -> productosMapper.modelToDto(productos))
                .collect(Collectors.toList());

        ProductosPaginacion ProductosPaginacion = new ProductosPaginacion();
        ProductosPaginacion.setContenido(contenido);
        ProductosPaginacion.setNumeroPagina(Productoss.getNumber());
        ProductosPaginacion.setMedidaPagina(Productoss.getSize());
        ProductosPaginacion.setTotalElementos(Productoss.getTotalElements());
        ProductosPaginacion.setTotalPaginas(Productoss.getTotalPages());
        ProductosPaginacion.setUltima(Productoss.isLast());

        return ProductosPaginacion;
    }

}
