package com.back.supermarket.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Carritos;

@Repository
@Transactional
public interface CarritosRepositorio extends CrudRepository<Carritos, Long> {
    List<Carritos> findByCedula(Long cedula);

    List<Carritos> deleteByCedula(Long cedula);
}
