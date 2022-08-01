package com.back.supermarket.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Personas;

@Repository
@Transactional
public interface PersonaRepositorio extends CrudRepository<Personas, Long> {

    Personas findByNombre(String nombre);

    @Query(value = "SELECT * FROM personas t WHERE t.cedula=cast(? as bigint) AND t.password=?", nativeQuery = true)
    Personas findByCedulaContraseña(Long cedula, String password);
}
