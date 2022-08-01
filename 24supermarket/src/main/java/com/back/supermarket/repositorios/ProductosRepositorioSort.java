package com.back.supermarket.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.supermarket.entidades.Productos;

@Repository
@Transactional
public interface ProductosRepositorioSort extends JpaRepository<Productos, Long> {

}
