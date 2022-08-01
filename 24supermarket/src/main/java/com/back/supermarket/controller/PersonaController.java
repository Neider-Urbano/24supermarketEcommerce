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

import com.back.supermarket.dto.PersonasDto;
import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.servicios.IPersonaServicio;

@RestController
@RequestMapping("api/v1/persona")
public class PersonaController {
    private ResponseDto responseDto;

    public PersonaController() {
        responseDto = new ResponseDto();
        responseDto.setMensaje("Proceso realizado correctamente");
    }

    @Autowired
    IPersonaServicio personaServicio;

    @GetMapping(path = "listar")
    public ResponseEntity<ResponseDto> listarPersonas() {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(personaServicio.listarPersonas());
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "buscar/{cedula}")
    public ResponseEntity<ResponseDto> buscarPersona(@PathVariable("cedula") Long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(personaServicio.buscarPersona(cedula));

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "buscar/{cedula}/contraseña/{contraseña}")
    public ResponseEntity<ResponseDto> buscarPersonaContraseña(@PathVariable("cedula") Long cedula,
            @PathVariable("contraseña") String password) {
        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(personaServicio.buscarPersonaContraseña(cedula,
                password));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<ResponseDto> guardar(@Valid @RequestBody PersonasDto personaDto) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        responseDto.setData(personaServicio.crearPersona(personaDto));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping(path = "actualizar/{cedula}")
    public ResponseEntity<ResponseDto> actulizarPersona(
            @Valid @RequestBody PersonasDto personaDto,
            @PathVariable("cedula") Long cedula) {

        responseDto.setCodigoRespuesta(HttpStatus.OK.value());
        responseDto.setData(personaServicio.actualizarPersona(personaDto, cedula));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "eliminar/{cedula}")
    public ResponseEntity<ResponseDto> elimininarPersona(@PathVariable("cedula") Long cedula) {
        responseDto.setCodigoRespuesta(HttpStatus.CREATED.value());
        personaServicio.eliminarPersona(cedula);
        responseDto.setData(null);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

}
