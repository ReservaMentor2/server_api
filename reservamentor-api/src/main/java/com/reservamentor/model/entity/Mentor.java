package com.reservamentor.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "Mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "valoracionPromedio", nullable = false)
    private Float valoracionPromedio;

    @OneToOne
    @JoinColumn(name = "usuarioID", referencedColumnName = "id", foreignKey = @ForeignKey(name = "usuarioID"))
    private Usuario usuario;
}
