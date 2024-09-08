package com.reservamentor.model.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;
}