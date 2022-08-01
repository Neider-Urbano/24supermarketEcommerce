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

import com.back.supermarket.dto.CarritosDto;
import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.servicios.ICarritosServicio;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritosController {
    private ResponseDto responseDto;

    public CarritosController() {
        responseDto = new ResponseDto();
        responseDto.setMensaje("Proceso realizado correctamente");
    }

    @Autowired
    ICarritosServicio carritoServicio;

    @GetMapping(path = "listar")
    public ResponseEntity<ResponseDto> listarCarritos() {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(carritoServicio.listarCarritos());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<ResponseDto> crearCarrito(@Valid @RequestBody CarritosDto carritoDto) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(carritoServicio.crearCarrito(carritoDto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "buscar/{carritoid}")
    public ResponseEntity<ResponseDto> buscarCarrito(@PathVariable("carritoid") long carritoid) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(carritoServicio.buscarCarrito(carritoid));

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "listar/persona/{cedula}")
    public ResponseEntity<ResponseDto> buscarCarritoPorCedula(@PathVariable("cedula") long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(carritoServicio.buscarCarritoPorCedula(cedula));

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping(path = "actualizar/{carritoid}")
    public ResponseEntity<ResponseDto> actulizarCarrito(
            @Valid @RequestBody CarritosDto CarritoDto,
            @PathVariable("carritoid") long carritoid) {

        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(carritoServicio.actualizarCarrito(CarritoDto,
                carritoid));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "eliminar/{carritoid}")
    public ResponseEntity<ResponseDto> elimininarCarrito(@PathVariable("carritoid") long carritoid) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        carritoServicio.eliminarCarrito(carritoid);
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "eliminar/persona/{cedula}")
    public ResponseEntity<ResponseDto> elimininarCarritoCedula(@PathVariable("cedula") long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        carritoServicio.eliminarCarritoCedula(cedula);
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
