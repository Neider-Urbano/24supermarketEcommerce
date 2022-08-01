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
import org.springframework.web.bind.annotation.RestController;

import com.back.supermarket.dto.CategoriasDto;
import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.servicios.ICategoriasServicio;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriasController {
    private ResponseDto responseDto;

    public CategoriasController() {
        responseDto = new ResponseDto();
        responseDto.setMensaje("Proceso realizado correctamente");
    }

    @Autowired
    ICategoriasServicio iCategoriasServicio;

    @GetMapping(path = "listar")
    public ResponseEntity<ResponseDto> listarCategorias() {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(iCategoriasServicio.listarCategorias());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("buscar/{categoriaid}")
    public ResponseEntity<CategoriasDto> listarCategoriaPorId(@PathVariable("categoriaid") Long categoriaid) {
        CategoriasDto categoriaDto = iCategoriasServicio.listarCategoriaPorId(categoriaid);
        return new ResponseEntity<>(categoriaDto, HttpStatus.OK);
    }

    @PostMapping(path = "crear")
    public ResponseEntity<ResponseDto> guardarCategoria(@Valid @RequestBody CategoriasDto categoriaDto) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(iCategoriasServicio.crearCategoria(categoriaDto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping("actualizar/{categoriaid}")
    public ResponseEntity<CategoriasDto> actualizarCategoria(@PathVariable("categoriaid") Long categoriaid,
            @Valid @RequestBody CategoriasDto categoriaDto) {
        CategoriasDto categoriaDtoActualizar = iCategoriasServicio.actualizarCategoria(categoriaid, categoriaDto);
        return new ResponseEntity<>(categoriaDtoActualizar, HttpStatus.OK);
    }

    @DeleteMapping("delete/{categoriaid}")
    public ResponseEntity<ResponseDto> eliminarCategoria(@PathVariable(value = "categoriaid") Long categoriaid) {
        iCategoriasServicio.eliminarCategoria(categoriaid);
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
