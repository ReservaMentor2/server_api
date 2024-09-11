package com.reservamentor.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "usuarioid", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 100)
    @NotNull
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Size(max = 60)
    @NotNull
    @Column(name = "contrasenia", nullable = false, length = 60)
    private String contrasenia;

    @Size(max = 20)
    @NotNull
    @Column(name = "nacionalidad", nullable = false, length = 20)
    private String nacionalidad;

    @Size(max = 9)
    @NotNull
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @NotNull
    @Column(name = "rol", nullable = false, length = Integer.MAX_VALUE)
    private String rol;

}