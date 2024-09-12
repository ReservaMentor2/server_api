package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @Column(name = "estudianteid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuarioid", nullable = false)
    private com.reservamentor.model.entity.Usuario usuarioid;

}