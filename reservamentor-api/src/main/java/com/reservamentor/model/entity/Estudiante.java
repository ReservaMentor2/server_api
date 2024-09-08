package com.reservamentor.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuarioID", referencedColumnName = "id", foreignKey = @ForeignKey(name = "usuarioID"))
    private Usuario usuario;
}
