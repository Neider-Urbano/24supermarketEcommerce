package com.back.supermarket.servicios;

import java.util.List;

import com.back.supermarket.dto.DireccionesDto;

public interface IDireccionesServicio {
    DireccionesDto crearDireccion(Long cedula, DireccionesDto direccionesDto);

    List<DireccionesDto> listarDireccionesPorCedula(Long cedula);

    DireccionesDto listarDireccionPorId(Long cedula, Long direccionid);

    public DireccionesDto actualizarDireccion(Long cedula, Long direccionid,
            DireccionesDto direccionDto);

    public void eliminarDireccion(Long cedula, Long direccionid);
}
