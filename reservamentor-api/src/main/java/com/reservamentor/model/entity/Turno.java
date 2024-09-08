package com.reservamentor.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @Column(name = "turnoid", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "turno", nullable = false)
    private Integer turno;

}