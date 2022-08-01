package com.back.supermarket.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Productos;

@Repository
@Transactional
public interface ProductosRepositorio extends CrudRepository<Productos, Long> {
    @Query(value = "SELECT * FROM productos t WHERE t.categoriaid=cast(? as bigint)", nativeQuery = true)
    List<Productos> findByCategoria(Long categoriaid);

    List<Productos> deleteByCedula(Long cedula);

    List<Productos> findByCedula(Long cedula);
}