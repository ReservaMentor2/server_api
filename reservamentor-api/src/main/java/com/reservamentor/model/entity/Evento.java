package com.reservamentor.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @Column(name = "evento_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Size(max = 500)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Column(name = "esvirtual", nullable = false)
    private Boolean esvirtual = false;

    @Size(max = 200)
    @NotNull
    @Column(name = "ubicacion", nullable = false, length = 200)
    private String ubicacion;

}