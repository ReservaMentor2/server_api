package com.reservamentor.model.entity;

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
    @ManyToOne
    @JoinColumn(name = "mentorid", nullable = false)
    private Mentor mentorid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "estudianteid", nullable = false)
    private Estudiante estudianteid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "asignaturaid", nullable = false)
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
    @ManyToOne
    @JoinColumn(name = "turnoid", nullable = false)
    private com.reservamentor.model.entity.Turno turnoid;

    @NotNull
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

}