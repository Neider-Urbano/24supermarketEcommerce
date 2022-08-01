package com.back.supermarket.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carritos")
public class Carritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carritoid;
    @Column(name = "cedula", nullable = false)
    private Long cedula;
    @Column(name = "productoid", nullable = false)
    private Long productoid;
    @Column(name = "cantidadproducto", nullable = false)
    private Long cantidadproducto;
    @Column(name = "nombreproducto", nullable = false)
    private String nombreproducto;
}
