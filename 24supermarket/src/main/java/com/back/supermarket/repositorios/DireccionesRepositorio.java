package com.back.supermarket.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Direcciones;

@Repository
@Transactional
public interface DireccionesRepositorio extends CrudRepository<Direcciones, Long> {
    @Query(value = "SELECT * FROM direcciones t WHERE t.cedula=cast(? as bigint)", nativeQuery = true)
    List<Direcciones> findByCedula(Long cedula);
}
