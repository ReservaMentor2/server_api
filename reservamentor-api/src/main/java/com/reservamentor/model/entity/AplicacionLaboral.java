package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "aplicacion_laboral")
public class AplicacionLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aplicacion_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "oportunidad_id", nullable = false)
    private OportunidadLaboral oportunidadLaboral;

    @NotNull
    @Column(name = "candidato", nullable = false)
    private String candidato; // Puedes usar una entidad Usuario si ya existe

    @NotNull
    @Column(name = "fecha_aplicacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAplicacion;

    @Column(name = "estado_aplicacion", nullable = false)
    private String estadoAplicacion; // Por ejemplo: "En Proceso", "Aceptada", "Rechazada"
}
