package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "nacionalidad", nullable = true)
    private String nacionalidad;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @Column(name = "imageProfile", nullable = true)
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role rol;
}
