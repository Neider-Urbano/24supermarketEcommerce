package com.back.supermarket.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "personas", indexes = { @Index(columnList = "cedula", unique = true) })
public class Personas {
    @Id
    private Long cedula;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    // relacion una persona a muchas direcciones
    @JsonBackReference
    @OneToMany(mappedBy = "personas", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Direcciones> direcciones = new HashSet<>();
}
