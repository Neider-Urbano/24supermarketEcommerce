package com.back.supermarket.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Categorias;

@Repository
@Transactional
public interface CategoriasRepositorio extends CrudRepository<Categorias, Long> {

}