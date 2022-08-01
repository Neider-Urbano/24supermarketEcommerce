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

import com.back.supermarket.dto.DireccionesDto;
import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.servicios.IDireccionesServicio;

@RestController
@RequestMapping("api/v1/direcciones")
public class DireccionesController {
    private ResponseDto responseDto;

    public DireccionesController() {
        responseDto = new ResponseDto();
        responseDto.setMensaje("Proceso realizado correctamente");
    }

    @Autowired
    IDireccionesServicio iDireccionesServicio;

    @GetMapping(path = "listar/persona/{cedula}")
    public ResponseEntity<ResponseDto> listarDirecciones(@PathVariable("cedula") Long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(iDireccionesServicio.listarDireccionesPorCedula(cedula));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("persona/{cedula}/direccion/{direccionid}")
    public ResponseEntity<DireccionesDto> listarDireccionPorId(@PathVariable("cedula") Long cedula,
            @PathVariable("direccionid") Long direccionid) {
        DireccionesDto direccionDto = iDireccionesServicio.listarDireccionPorId(cedula, direccionid);
        return new ResponseEntity<>(direccionDto, HttpStatus.OK);
    }

    @PostMapping(path = "crear/{cedula}")
    public ResponseEntity<ResponseDto> guardarDireccion(@PathVariable("cedula") Long cedula,
            @Valid @RequestBody DireccionesDto direccionesDto) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(iDireccionesServicio.crearDireccion(cedula,
                direccionesDto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping("actualizar/persona/{cedula}/direccion/{direccionid}")
    public ResponseEntity<DireccionesDto> actualizarDireccion(@PathVariable("cedula") Long cedula,
            @PathVariable("direccionid") Long direccionid, @Valid @RequestBody DireccionesDto direccionDto) {
        DireccionesDto direccionDtoActualizar = iDireccionesServicio.actualizarDireccion(cedula, direccionid,
                direccionDto);
        return new ResponseEntity<>(direccionDtoActualizar, HttpStatus.OK);
    }

    @DeleteMapping("delete/persona/{cedula}/direccion/{direccionid}")
    public ResponseEntity<ResponseDto> eliminarDireccion(@PathVariable(value = "cedula") Long cedula,
            @PathVariable(value = "direccionid") Long direccionid) {

        iDireccionesServicio.eliminarDireccion(cedula, direccionid);
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
