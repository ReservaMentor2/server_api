package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mentor_certificacion")
public class MentorCertificacion {
    @EmbeddedId
    private MentorCertificacionId id;

    @MapsId("mentorid")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private Mentor mentorid;

    @MapsId("certificacionid")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "certificacionid", nullable = false)
    private Certificacion certificacionid;

    @NotNull
    @Column(name = "fechaobtencion", nullable = false)
    private LocalDate fechaobtencion;

}