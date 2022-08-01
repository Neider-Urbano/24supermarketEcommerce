package com.back.supermarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back.supermarket.dto.ProductosDto;
import com.back.supermarket.dto.ProductosPaginacion;
import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.servicios.IProductosServicio;
import com.back.supermarket.utilerias.AppConstantes;

@RestController
@RequestMapping("api/v1/productos")
public class ProductosController {
    private ResponseDto responseDto;

    public ProductosController() {
        responseDto = new ResponseDto();
        responseDto.setMensaje("Proceso realizado correctamente");
    }

    @Autowired
    IProductosServicio iProductosServicio;

    @GetMapping(path = "listar")
    public ResponseEntity<ResponseDto> listarProductos() {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(iProductosServicio.listarProductos());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("buscar/{productoid}")
    public ResponseEntity<ResponseDto> obtenerProductoPorId(@PathVariable("productoid") Long productoid) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        ProductosDto ProductoDto = iProductosServicio.buscarProducto(productoid);
        responseDto.setData(ProductoDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "listar/categoria/{categoriaid}")
    public ResponseEntity<ResponseDto> buscarProductosPorCategoria(@PathVariable("categoriaid") Long categoriaid) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(iProductosServicio.buscarProductosPorCategoria(categoriaid));

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "listar/persona/{cedula}")
    public ResponseEntity<ResponseDto> buscarProductosPorCedula(@PathVariable("cedula") Long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(iProductosServicio.buscarProductosPorCedula(cedula));

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "listarPaginado")
    public ProductosPaginacion listarProductossPaginacion(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return iProductosServicio.listarProductosPaginacion(numeroDePagina,
                medidaDePagina, ordenarPor, sortDir);
    }

    @PostMapping(path = "crear/categoria/{categoriaid}")
    public ResponseEntity<ResponseDto> crearProducto(@Valid @RequestBody ProductosDto productoDto,
            @PathVariable("categoriaid") Long categoriaid) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(iProductosServicio.crearProducto(productoDto,
                categoriaid));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping("actualizar/{productoid}")
    public ResponseEntity<ResponseDto> actualizarProducto(@PathVariable("productoid") Long productoid,
            @Valid @RequestBody ProductosDto productoDto) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(iProductosServicio.actualizarProducto(productoDto, productoid));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{productoid}")
    public ResponseEntity<ResponseDto> eliminarProducto(@PathVariable(value = "productoid") Long productoid) {
        iProductosServicio.eliminarProducto(productoid);
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/persona/{cedula}")
    public ResponseEntity<ResponseDto> eliminarProductoCedula(@PathVariable(value = "cedula") Long cedula) {
        iProductosServicio.eliminarProductoCedula(cedula);
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
