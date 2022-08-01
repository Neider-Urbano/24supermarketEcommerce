package com.back.supermarket.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "direcciones")
public class Direcciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long direccionid;
    @Column(name = "direccion", nullable = false)
    private String direccion;

    // relacion muchas direcciones a una persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedula", nullable = false)
    private Personas personas;
}
