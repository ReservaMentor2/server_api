package com.reservamentor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "sesionmentoria")
public class SesionMentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesionmentoriaid", nullable = false)
    private Integer id; // ok

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    @JsonIgnore
    private Mentor mentorid; // ok

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estudianteid", nullable = false)
    @JsonIgnore
    private Estudiante estudianteid; // ok

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "asignaturaid", nullable = false)
    @JsonIgnore
    private Asignatura asignaturaid; // en duda

    @NotNull
    @Column(name = "dia", nullable = false)
    private LocalDate dia; // ok

    @NotNull
    @Column(name = "horainicio", nullable = false)
    private LocalTime horainicio; // ok

    @NotNull
    @Column(name = "horafinal", nullable = false)
    private LocalTime horafinal; // ok

    @Size(max = 200)
    @NotNull
    @Column(name = "weblink", nullable = false, length = 200)
    private String weblink; // ok

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "turnoid", nullable = false)
    @JsonIgnore
    private Turno turnoid; // en duda

    @NotNull
    @Column(name = "precio", nullable = false)
    private Integer precio; // ok

    @NotNull
    @Column
    private String titulo; // ok

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // no necesario

    private String slug; // no necesario

}
