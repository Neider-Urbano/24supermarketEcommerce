package com.back.supermarket.configuraciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.back.supermarket.dto.ResponseDto;
import com.back.supermarket.excepciones.ResourceNotFoundException;

@ControllerAdvice
public class ControllerManejoExcepciones extends ResponseEntityExceptionHandler {
    private ResponseDto responseDto;

    public ControllerManejoExcepciones() {
        responseDto = new ResponseDto();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            org.springframework.web.bind.MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, org.springframework.http.HttpStatus status,
            org.springframework.web.context.request.WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        responseDto.setCodigoRespuesta(HttpStatus.BAD_REQUEST.value());
        responseDto.setMensaje("Error interno de la aplicación");
        responseDto.setData(errors);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> globalExceptionHandler(Exception ex, WebRequest request) {
        responseDto.setCodigoRespuesta(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseDto.setMensaje(ex.getMessage());
        responseDto.setData(LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class })
    public ResponseEntity<ResponseDto> handledConstraintViolationException(Exception ex, WebRequest request) {
        responseDto.setCodigoRespuesta(HttpStatus.CONFLICT.value());
        responseDto.setMensaje("Error registro duplicado");
        responseDto.setData(LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> handledNoDataFoundException(ResourceNotFoundException ex, WebRequest request) {
        responseDto.setCodigoRespuesta(HttpStatus.NOT_FOUND.value());
        responseDto.setMensaje("Error registro no existe");
        responseDto.setData(LocalDateTime.now());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
