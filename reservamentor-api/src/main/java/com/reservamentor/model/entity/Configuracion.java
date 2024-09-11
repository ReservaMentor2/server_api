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
@Table(name = "configuracion")
public class Configuracion {
    @Id
    @Column(name = "configuracionid", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "modooscuro", nullable = false)
    private Boolean modooscuro = false;

    @NotNull
    @Column(name = "notificacionesactivas", nullable = false)
    private Boolean notificacionesactivas = false;

    @Size(max = 20)
    @NotNull
    @Column(name = "idioma", nullable = false, length = 20)
    private String idioma;

}