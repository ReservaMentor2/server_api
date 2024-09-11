package com.reservamentor.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asignatura_mentor")
public class AsignaturaMentor {
    @EmbeddedId
    private AsignaturaMentorId id;

    @MapsId("asignaturaid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asignaturaid", nullable = false)
    private Asignatura asignaturaid;

    @MapsId("mentorid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private com.reservamentor.model.entity.Mentor mentorid;

}