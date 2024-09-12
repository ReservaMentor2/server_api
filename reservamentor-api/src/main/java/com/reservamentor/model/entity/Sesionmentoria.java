package com.reservamentor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "sesionmentoria")
public class Sesionmentoria {
    @Id
    @Column(name = "sesionmentoriaid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    @JsonIgnore
    private Mentor mentorid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudianteid", nullable = false)
    @JsonIgnore
    private Estudiante estudianteid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asignaturaid", nullable = false)
    @JsonIgnore
    private Asignatura asignaturaid;

    @NotNull
    @Column(name = "dia", nullable = false)
    private LocalDate dia;

    @NotNull
    @Column(name = "horainicio", nullable = false)
    private LocalTime horainicio;

    @NotNull
    @Column(name = "horafinal", nullable = false)
    private LocalTime horafinal;

    @Size(max = 200)
    @NotNull
    @Column(name = "weblink", nullable = false, length = 200)
    private String weblink;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turnoid", nullable = false)
    @JsonIgnore
    private com.reservamentor.model.entity.Turno turnoid;

    @NotNull
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

}