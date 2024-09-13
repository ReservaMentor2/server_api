package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asistencia_evento")
public class AsistenciaEvento {
    @EmbeddedId
    private AsistenciaEventoId id;

    @MapsId("mentorid")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private com.reservamentor.model.entity.Mentor mentorid;

    @MapsId("eventoid")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "eventoid", nullable = false)
    private com.reservamentor.model.entity.Evento eventoid;

    @NotNull
    @Column(name = "asistenciaconfirmada", nullable = false)
    private Boolean asistenciaconfirmada = false;

}