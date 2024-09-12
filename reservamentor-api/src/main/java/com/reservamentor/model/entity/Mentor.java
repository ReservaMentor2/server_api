package com.reservamentor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @Column(name = "mentorid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuarioid", nullable = false)
    private com.reservamentor.model.entity.Usuario usuarioid;

    @NotNull
    @Column(name = "valoracionpromedio", nullable = false, precision = 4, scale = 2)
    private BigDecimal valoracionpromedio;

    @NotNull
    @Column(name = "tarifahora", nullable = false)
    private Integer tarifahora;

    @Size(max = 500)
    @NotNull
    @Column(name = "biografia", nullable = false, length = 500)
    private String biografia;

}