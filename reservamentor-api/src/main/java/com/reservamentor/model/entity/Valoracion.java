package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "valoracion")
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asegúrate de generar el ID automáticamente si es necesario
    @Column(name = "valoracionid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estudianteid", nullable = false)
    private Estudiante estudiante;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private Mentor mentor;

    @Size(max = 200)
    @NotNull
    @Column(name = "comentario", nullable = false, length = 200)
    private String comentario;

    @NotNull
    @Column(name = "estrellas", nullable = false)
    private Integer estrellas;
}
