package com.back.supermarket.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoid;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    @Column(name = "imgurl", nullable = true)
    private String imgurl;
    @Column(name = "cedula", nullable = false)
    private Long cedula;

    @ManyToOne
    @JoinColumn(name = "categoriaid")
    Categorias categorias;
}
