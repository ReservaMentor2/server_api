package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorito")
public class Favorito {
    @Id
    @Column(name = "favoritoid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estudianteid", nullable = false)
    private Estudiante estudianteid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mentorid", nullable = false)
    private com.reservamentor.model.entity.Mentor mentorid;

}