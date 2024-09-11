package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "participacion_evento")
public class ParticipacionEvento {
    @EmbeddedId
    private ParticipacionEventoId id;

    @MapsId("mentorid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private Mentor mentorid;

    @MapsId("eventoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @NotNull
    @Column(name = "asistenciaconfirmada", nullable = false)
    private Boolean asistenciaconfirmada = false;

}